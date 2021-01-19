package com.xianyu.yixian_client.Frame.BattleRepository;

import android.util.Pair;

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
    public MutableLiveData<ArrayList<Pair<CardGroup,ArrayList<SkillCard>>>> cardGroups_live = new MutableLiveData<>();
    private Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
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
    public void refreshAllSkillCards(){
        disposable.add(repository.queryAllSkillCards().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> skillcards_live.postValue(value)));
    }
    public void refreshCardGroups(){
        ArrayList<Pair<CardGroup,ArrayList<SkillCard>>> parent_cardGroups = new ArrayList<>();
        for (CardGroup cardGroup : Core.liveUser.getValue().getCardGroups()){
            Pair<CardGroup,ArrayList<SkillCard>> cards = new Pair<>(cardGroup,new ArrayList<>());
            parent_cardGroups.add(cards);
            for(long id : cardGroup.getCards_id()){
               disposable.add(repository.querySkillCardById(id)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(skillCard ->{
                           cards.second.add(skillCard);
                           cardGroups_live.postValue(parent_cardGroups);
                       }));
            }
        }
    }
}
