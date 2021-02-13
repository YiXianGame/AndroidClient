package com.yixian.make.Repository;

import android.util.Log;

import com.yixian.make.RPC.Adapt.SkillCardAdapt;
import com.yixian.make.RPC.Adapt.UserAdapt;
import com.yixian.material.Entity.User;
import com.yixian.material.Log.Log.Tag;
import com.yixian.make.RPC.Request.SkillCardRequest;
import com.yixian.material.RPC.RPCServerFactory;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.RPCRequestProxyFactory;
import com.yixian.material.RPC.RPCType;
import com.yixian.make.RPC.Request.UserRequest;
import com.yixian.make.Repository.Interface.IRemoteRepository;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.RemoteRepository
 * @ClassName: RemoteRepository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/20 15:31
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/20 15:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RemoteRepository implements IRemoteRepository {
    public UserRequest userRequest;
    public SkillCardRequest skillCardRequest;

    public RemoteRepository(){
        RPCType type = new RPCType();
        try{
            type.add(Integer.class,"int");
            type.add(String.class,"string");
            type.add(Boolean.class,"bool");
            type.add(Long.class,"long");
            type.add(SkillCard.class,"skillCard");
            type.add(User.class,"user");
            type.add(new ArrayList<SkillCard>(){}.getClass().getGenericSuperclass(),"skillcards");
        }
        catch (RPCException e){
            Log.e(Tag.RemoteRepository,e.getMessage());
            e.printStackTrace();
        }
        //每一个Service都可以拥有自己的TypeConvert.
        userRequest = RPCRequestProxyFactory.Register(UserRequest.class,"UserServer","192.168.0.105","28015",type);
        RPCServerFactory.Register(UserAdapt.class,"UserClient","192.168.0.105","28015",type);
        skillCardRequest = RPCRequestProxyFactory.Register(SkillCardRequest.class,"SkillCardServer","192.168.0.105","28015",type);
        RPCServerFactory.Register(SkillCardAdapt.class,"SkillCardClient","192.168.0.105","28015",type);
        //Observable.create((ObservableOnSubscribe<Void>) emitter -> Log.d(Tag.Debug,userDao.hello("你好"))).subscribeOn(Schedulers.io()).subscribe();
    }
}
