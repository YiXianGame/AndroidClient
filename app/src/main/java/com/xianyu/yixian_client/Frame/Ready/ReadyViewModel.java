package com.xianyu.yixian_client.Frame.Ready;

import android.app.DownloadManager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Frame.Ready.Adapt.RPC.ReadyAdapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.RPC.ReadyRequest;
import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.RPCAdaptFactory;
import com.yixian.material.RPC.RPCRequestProxyFactory;
import com.yixian.material.RPC.RPCType;

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
    public ReadyAdapt readyAdapt;
    public MutableLiveData<Map<Long, UserWithCardGroupItem>> liveTeammates = new MutableLiveData<>();
    public MutableLiveData<Map<Long, UserWithCardGroupItem>> liveEnemies = new MutableLiveData<>();
    public boolean confirm = false;
    public void initialization(Repository repository){
        this.repository = repository;
        RPCType type = new RPCType();
        try{
            type.add(Integer.class,"int");
            type.add(String.class,"string");
            type.add(Boolean.class,"bool");
            type.add(Long.class,"long");
            type.add(SkillCard.class,"skillCard");
            type.add(User.class,"user");
            type.add(CardGroup.class,"cardGroup");
            type.add(new ArrayList<Long>(){}.getClass().getGenericSuperclass(),"longs");
            type.add(new ArrayList<SkillCard>(){}.getClass().getGenericSuperclass(),"skillCards");
            type.add(new ArrayList<CardItem>(){}.getClass().getGenericSuperclass(),"cardItem");
            type.add(new ArrayList<CardGroup>(){}.getClass().getGenericSuperclass(),"cardGroups");
            type.add(new ArrayList<Friend>(){}.getClass().getGenericSuperclass(),"friends");
            type.add(new ArrayList<User>(){}.getClass().getGenericSuperclass(),"users");
        } catch (RPCException e) {
            e.printStackTrace();
        }
        readyAdapt = new ReadyAdapt(this);
        RPCAdaptFactory.register(readyAdapt,"ReadyClient", Core.userServer.first,Core.userServer.second,type);
        readyRequest = RPCRequestProxyFactory.register(ReadyRequest.class,"ReadyServer", Core.userServer.first,Core.userServer.second,type);
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
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
        RPCAdaptFactory.destory("ReadyClient", Core.userServer.first,Core.userServer.second);
        RPCRequestProxyFactory.destory("ReadyServer", Core.userServer.first,Core.userServer.second);
    }
}
