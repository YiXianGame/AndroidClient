package com.xianyu.yixian_client.Frame.Ready;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.MessageDialog;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.User;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class ReadyViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public Room.RoomType roomType;
    public MutableLiveData<ArrayList<User>> liveReadyUsers;
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<Boolean> inviteFriend(long id){
        return repository.userRepository.invite(id);
    }
    public Single<Boolean> startMatch(long id,String roomType){
        return repository.userRepository.startMatch(id,roomType);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
