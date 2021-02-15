package com.xianyu.yixian_client.Frame.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.repository
 * @ClassName: RepositoryViewModel
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/31 8:36
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/31 8:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RepositoryViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public RepositoryViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
