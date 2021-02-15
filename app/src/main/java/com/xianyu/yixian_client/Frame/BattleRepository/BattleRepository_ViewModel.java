package com.xianyu.yixian_client.Frame.BattleRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.User;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BattleRepository_ViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public BattleRepository_ViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }

    public void updateUserAttribute(User user){
        repository.userRepository.updateCardGroups(user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
