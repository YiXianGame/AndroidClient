package com.xianyu.yixian_client.Frame.FriendSystem;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Friend_ViewModel extends ViewModel {
    private Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<User>> friends_live = new MutableLiveData<>();
    public Friend_ViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<List<User>> queryFriendUsers(long user_id){
        return repository.queryAllFriendUsers(user_id);
    }
    public void refreshFriends(long id){
        disposable.add(repository.queryAllFriendUsers(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(friends -> {
                    friends_live.postValue(friends);
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
