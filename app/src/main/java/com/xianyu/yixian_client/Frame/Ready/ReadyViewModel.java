package com.xianyu.yixian_client.Frame.Ready;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.RPC.EquipRequest;
import com.xianyu.yixian_client.Frame.Ready.RPC.ReadyRequest;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Player;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Request.RPCNetRequestConfig;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

public class ReadyViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    private Room.RoomType roomType;
    private ReadyRequest readyRequest;
    private EquipRequest equipRequest;
    private MutableLiveData<ArrayList<Team>> liveTeams = new MutableLiveData<>();
    private Player player;
    private boolean confirm = false;

    public Room.RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(Room.RoomType roomType) {
        this.roomType = roomType;
    }

    public ReadyRequest getReadyRequest() {
        return readyRequest;
    }

    public void setReadyRequest(ReadyRequest readyRequest) {
        this.readyRequest = readyRequest;
    }

    public EquipRequest getEquipRequest() {
        return equipRequest;
    }

    public void setEquipRequest(EquipRequest equipRequest) {
        this.equipRequest = equipRequest;
    }

    public MutableLiveData<ArrayList<Team>> getLiveTeams() {
        return liveTeams;
    }

    public void setLiveTeams(MutableLiveData<ArrayList<Team>> liveTeams) {
        this.liveTeams = liveTeams;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public void initialization(Repository repository){
        this.repository = repository;
        //初始化RPC
        RPCType type = new RPCType();
        try{
            type.add(Integer.class,"Int");
            type.add(String.class,"String");
            type.add(Boolean.class,"Bool");
            type.add(Long.class,"Long");
            type.add(User.class,"User");
            type.add(CardGroup.class,"CardGroup");
            type.add(new ArrayList<Long>(){}.getClass().getGenericSuperclass(),"List<long>");
            type.add(new ArrayList<User>(){}.getClass().getGenericSuperclass(),"List<User>");
            type.add(new ArrayList<Team>(){}.getClass().getGenericSuperclass(),"List<Team>");
        } catch (RPCException e) {
            e.printStackTrace();
        }
        RPCNetRequestConfig config = new RPCNetRequestConfig(type);
        this.readyRequest = RPCNetRequestFactory.register(ReadyRequest.class,"ReadyServer",Core.userServer.first,Core.userServer.second,config);
        this.equipRequest = RPCNetRequestFactory.register(EquipRequest.class,"EquipServer",Core.userServer.first,Core.userServer.second,config);
    }
    public Single<Boolean> inviteFriend(long id){
        return new Single<Boolean>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super Boolean> observer) {
                observer.onSuccess(readyRequest.InviteSquad(id));
            }
        };
    }
    public void startMatch(){
        readyRequest.StartMatch();
    }
    public Single<ArrayList<User>> enterSquad(long id,String secretKey){
        return new Single<ArrayList<User>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super ArrayList<User>> observer) {
                observer.onSuccess(readyRequest.EnterSquad(id,secretKey));
            }
        };
    }
    public Single<Boolean> confirmCardGroup(){
        return new Single<Boolean>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super Boolean> observer) {
                observer.onSuccess(equipRequest.ConfirmCardGroup());
            }
        };
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
        RPCNetServiceFactory.destory("ReadyClient", Core.userServer.first,Core.userServer.second);
        RPCNetRequestFactory.destory("ReadyServer", Core.userServer.first,Core.userServer.second);
    }
}
