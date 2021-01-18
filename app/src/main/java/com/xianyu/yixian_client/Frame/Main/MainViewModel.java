package com.xianyu.yixian_client.Frame.Main;

import androidx.lifecycle.MutableLiveData;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();
    @Inject
    Repository repository;
    public MainViewModel(){

    }
}
