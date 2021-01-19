package com.xianyu.yixian_client.Frame.FriendSystem;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class Friend_ViewModel extends ViewModel {
    private Repository repository;
    public Friend_ViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
}
