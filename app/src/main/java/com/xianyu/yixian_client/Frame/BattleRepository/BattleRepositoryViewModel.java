package com.xianyu.yixian_client.Frame.BattleRepository;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

public class BattleRepositoryViewModel extends ViewModel {
    public Repository repository;
    @Inject
    public BattleRepositoryViewModel(Repository repository){
        this.repository = repository;
    }

}
