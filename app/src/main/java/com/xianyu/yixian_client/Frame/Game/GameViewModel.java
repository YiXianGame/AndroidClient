package com.xianyu.yixian_client.Frame.Game;

import androidx.lifecycle.ViewModel;

import com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC.LoadRequest;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;


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
public class GameViewModel extends ViewModel {
    public Repository repository;
    public LoadRequest loadRequest;

    public void initialization(Repository repository){
        this.repository = repository;
        //初始化RPC
        RPCType type = new RPCType();
        try{
            type.add(Integer.class,"Int");
            type.add(String.class,"String");
            type.add(Boolean.class,"Bool");
            type.add(Long.class,"Long");
            type.add(User.class,"User");
            type.add(CardGroup.class,"CardGroup");
            type.add(new ArrayList<Long>(){}.getClass().getGenericSuperclass(),"List<long>");
            type.add(new ArrayList<User>(){}.getClass().getGenericSuperclass(),"List<User>");
            type.add(new ArrayList<Team>(){}.getClass().getGenericSuperclass(),"List<Team>");
        } catch (RPCException e) {
            e.printStackTrace();
        }
        this.loadRequest = RPCNetRequestFactory.register(LoadRequest.class,"LoadRequest", Core.playerServer.first,Core.playerServer.second,type);
    }
}

