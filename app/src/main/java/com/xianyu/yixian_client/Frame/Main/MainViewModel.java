package com.xianyu.yixian_client.Frame.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.make.Repository.ConfigRepository;
import com.yixian.material.Entity.User;
import com.yixian.material.Entity.Friend;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<List<User>> users_live = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<User>> friends_live = new MutableLiveData<>();
    private Repository repository;
    public MainViewModel(){

    }

    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<List<Friend>> queryFriends(long id){
        return repository.friendRepository.query(id);
    }
    public Single<List<User>> queryFriendUsers(long user_id){
        return repository.friendRepository.queryUsers(user_id);
    }
    public void refreshFriends(long id){
        disposable.add(repository.friendRepository.queryUsers(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(friends -> {
                    friends_live.postValue(friends);
                }));
    }
    public void syncUserAttribute(MutableLiveData<User> user_live){
        disposable.add(repository.userRepository.syncAttribute(user_live.getValue()).subscribe(user_live::postValue));
    }
    public void syncSkillCard(MutableLiveData<User> user_live){
        disposable.add(repository.skillCardRepository.sync(Core.liveConfig.getValue().getSkillCardUpdate()).subscribe(list->{
           if(list!=null){
               
           }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
