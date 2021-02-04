package com.xianyu.yixian_client.Frame.BattleRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BattleRepository_ViewModel extends ViewModel {
    public MutableLiveData<List<SkillCard>> skillcards_live = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public BattleRepository_ViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<User> queryUserById(long id){
         return repository.queryUserById(id);
    }
    public Single<SkillCard> querySkillCardById(long id){
        return repository.querySkillCardById(id);
    }
    public void refreshUser(){
        disposable.add(repository.queryUserById(Core.liveUser.getValue().getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> Core.liveUser.postValue(user))
        );
    }
    public void updateUser(User user){

    }
    public void refreshSkillCards(){
        disposable.add(repository.queryAllSkillCards().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(skillCards -> {
                    skillcards_live.postValue(skillCards);
                }));
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
