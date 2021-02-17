package com.xianyu.yixian_client.Frame.Main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.make.Repository.ConfigRepository;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Exception.SkillCardException;
import com.yixian.material.Log.Log.Tag;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public MainViewModel(){

    }

    public void initialization(Repository repository){
        this.repository = repository;
    }

    public void syncUserAttribute(MutableLiveData<User> user_live){
        disposable.add(repository.userRepository.syncAttribute(user_live.getValue()).subscribe(user_live::postValue));
    }
    public void syncUserFriend(MutableLiveData<User> user_live){
        disposable.add(repository.userRepository.syncFriend(user_live.getValue()).subscribe(Core.liveFriends::postValue));
    }
    public void syncSkillCard(MutableLiveData<User> user_live){
        disposable.add(repository.skillCardRepository.sync(Core.liveConfig.getValue().getSkillCardUpdate()).subscribe(result ->{
            if(result){
                repository.userRepository.syncUserSkillCard(user_live.getValue()).subscribe(new MaybeObserver<ArrayList<SkillCard>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ArrayList<SkillCard> skillCards) {
                        Core.liveSkillcards.getValue().clear();
                        for(SkillCard item : skillCards)Core.liveSkillcards.getValue().put(item.getId(),item);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        try {
                            throw new SkillCardException(SkillCardException.SkillCardErrorCode.SyncUserError,"同步用户技能卡时发生异常");
                        } catch (SkillCardException e) {
                            Log.e(Tag.RemoteRepository,e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
            }
            else throw new SkillCardException(SkillCardException.SkillCardErrorCode.SyncError,"同步技能卡的时发生异常");
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
