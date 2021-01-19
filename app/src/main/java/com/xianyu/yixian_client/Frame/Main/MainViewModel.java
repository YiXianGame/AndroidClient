package com.xianyu.yixian_client.Frame.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Hilt.Application_Provider;
import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.EntryPointAccessors;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();
    private Repository repository;
    public MainViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
}
