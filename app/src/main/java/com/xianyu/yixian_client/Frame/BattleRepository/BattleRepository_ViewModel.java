package com.xianyu.yixian_client.Frame.BattleRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.User;
import com.yixian.material.Entity.SkillCard;

import java.util.List;

import io.reactivex.Maybe;
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
    public Maybe<User> queryUserById(long id){
         return repository.userRepository.local_queryById(id);
    }
    public Maybe<SkillCard> querySkillCardById(long id){
        return repository.skillCardRepository.queryById(id);
    }
    public void refreshUser(){
        disposable.add(repository.userRepository.local_queryById(Core.liveUser.getValue().getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> Core.liveUser.postValue(user))
        );
    }
    public void updateUser(User user){

    }
    public void refreshSkillCards(){
        disposable.add(repository.skillCardRepository.queryAll().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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
