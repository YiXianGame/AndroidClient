package com.xianyu.yixian_client.PersonalInformation.Fragment;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.RepositoryFactory;

import javax.inject.Inject;

public class PersonalInformationViewModel extends ViewModel {
    public RepositoryFactory repositoryFactory;
    @Inject
    public PersonalInformationViewModel(RepositoryFactory repositoryFactory){
        this.repositoryFactory = repositoryFactory;
    }

}
