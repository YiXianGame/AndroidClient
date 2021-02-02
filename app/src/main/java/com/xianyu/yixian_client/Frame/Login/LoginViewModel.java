package com.xianyu.yixian_client.Frame.Login;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


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
    public Repository repository;
    public void initialization(Repository repository){
        this.repository = repository;
    }
}

