package com.xianyu.yixian_client.Frame.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uber.autodispose.AutoDispose;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
    MutableLiveData<String> message = new MutableLiveData<>();
    MutableLiveData<List<SkillCard>> skillcards_live = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;
    public RepositoryViewModel(){

    }
    public void initialization(Repository repository){
        this.repository = repository;
    }
    public void refreshSkillCards(){
        disposable.add(repository.queryAllSkillCards().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(skillCards -> {
                    skillcards_live.postValue(skillCards);
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
