package com.xianyu.yixian_client.Frame.Login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import io.reactivex.disposables.CompositeDisposable;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Login
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
    private final CompositeDisposable disposable = new CompositeDisposable();
    public Repository repository;
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public void sync_User(MutableLiveData<User> user_live){
        disposable.add(repository.sync_UserAttribute(user_live.getValue()).subscribe(user_live::postValue));
    }
}

