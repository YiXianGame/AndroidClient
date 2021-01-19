package com.xianyu.yixian_client.Frame.PersonalInformation;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class PersonalProfileViewModel extends ViewModel {

    private Repository repository;
    public PersonalProfileViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
}
