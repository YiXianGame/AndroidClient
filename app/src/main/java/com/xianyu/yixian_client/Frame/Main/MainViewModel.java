package com.xianyu.yixian_client.Frame.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Hilt.Application_Provider;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.EntryPointAccessors;
import io.reactivex.Single;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();
    private Repository repository;
    public MainViewModel(){

    }

    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<List<Friend>> queryFriends(long id){
        return repository.queryFriends(id);
    }
    public Single<List<User>> queryFriendUsers(long user_id){
        return repository.queryAllFriendUsers(user_id);
    }
}
