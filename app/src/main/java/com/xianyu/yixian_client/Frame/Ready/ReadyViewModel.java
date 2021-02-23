package com.xianyu.yixian_client.Frame.Ready;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class ReadyViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public Room.RoomType roomType;
    public MutableLiveData<Map<Long, UserWithCardGroupItem>> liveTeammates = new MutableLiveData<>();
    public MutableLiveData<Map<Long, UserWithCardGroupItem>> liveEnemies = new MutableLiveData<>();
    public boolean sure = false;
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<Boolean> inviteFriend(long id){
        return repository.userRepository.invite(id);
    }
    public Single<String> createSquad(String roomType){
        return repository.userRepository.createSquad(roomType);
    }
    public Single<ArrayList<User>> startMatch(ArrayList<Long> users){
        return repository.userRepository.queryUsers(users);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
