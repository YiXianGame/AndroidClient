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
    public Room.RoomType roomType;
    public ReadyRequest readyRequest;
    public EquipRequest equipRequest;
    public MutableLiveData<Map<Long, Player>> liveTeammates = new MutableLiveData<>();
    public MutableLiveData<Map<Long, Player>> liveEnemies = new MutableLiveData<>();
    public boolean confirm = false;
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
        this.readyRequest = RPCNetRequestFactory.register(ReadyRequest.class,"ReadyRequest",Core.userServer.first,Core.userServer.second,type);
        this.equipRequest = RPCNetRequestFactory.register(EquipRequest.class,"EquipRequest",Core.userServer.first,Core.userServer.second,type);
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
                observer.onSuccess(readyRequest.ConfirmCardGroup());
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
