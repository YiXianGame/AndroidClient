package com.xianyu.yixian_client.Frame.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

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
@AndroidEntryPoint
public class RepositoryViewModel extends ViewModel {
    MutableLiveData<String> message = new MutableLiveData<>();
    @Inject
    Repository repository;
    public RepositoryViewModel(){

    }
    public ArrayList<SkillCard> getCards(){
        return null;
    }
}
