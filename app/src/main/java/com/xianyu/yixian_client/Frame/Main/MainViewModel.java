package com.xianyu.yixian_client.Frame.Main;

import androidx.lifecycle.MutableLiveData;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

public class MainViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();
    Repository repository;
    @Inject
    public MainViewModel(Repository repository){
        this.repository = repository;
    }
}
