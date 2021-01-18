package com.xianyu.yixian_client.Frame.FriendSystem;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Friend_ViewModel extends ViewModel {
    @Inject
    Repository repository;
    Friend_ViewModel(){

    }
}
