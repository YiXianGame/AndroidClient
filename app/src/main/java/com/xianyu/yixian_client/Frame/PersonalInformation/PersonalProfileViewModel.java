package com.xianyu.yixian_client.Frame.PersonalInformation;

import androidx.lifecycle.ViewModel;

import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.User;

import io.reactivex.Maybe;

public class PersonalProfileViewModel extends ViewModel {

    private Repository repository;
    public PersonalProfileViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public Maybe<User> queryUserByID(long user_id){
        return repository.userRepository.local_queryById(user_id);
    }
}
