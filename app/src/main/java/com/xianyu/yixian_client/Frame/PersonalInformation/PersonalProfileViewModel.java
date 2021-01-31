package com.xianyu.yixian_client.Frame.PersonalInformation;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Single;

public class PersonalProfileViewModel extends ViewModel {

    private Repository repository;
    public PersonalProfileViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Single<User> queryUserByID(long user_id){
        return repository.queryUserById(user_id);
    }
}
