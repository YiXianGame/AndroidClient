package com.xianyu.yixian_client.Frame.FriendSystem;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class Friend_ViewModel extends ViewModel {
    private Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public Friend_ViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<List<User>> queryFriendUsers(long user_id){
        return repository.queryAllFriendUsers(user_id);
    }
}
