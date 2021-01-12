package com.xianyu.yixian_client.Login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.RepositoryFactory;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Login
 * @ClassName: RepositoryViewModel
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/19 17:34
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/19 17:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> verificationCode = new MutableLiveData<String>();
    public MutableLiveData<String> surePassword = new MutableLiveData<String>();

    public RepositoryFactory repositoryFactory;
    @Inject
    public LoginViewModel(RepositoryFactory repositoryFactory){
        this.repositoryFactory = repositoryFactory;
    }

    public void insertUser(User user){
        repositoryFactory.insertUser(user);
    }
    public void deleteUser(User user){
        repositoryFactory.deleteUser(user);
    }
    public void updateUser(User user){
        repositoryFactory.updateUser(user);
    }
    public void clearAllUser(User user){
        repositoryFactory.updateUser(user);
    }
    public Single<List<User>> query_Users(){
       return repositoryFactory.queryUsers();
    }
    public void ValidUser(User user){

    }
    public void RegisterUser(User user){

    }
    public void ChangeUser(User user){

    }

}

