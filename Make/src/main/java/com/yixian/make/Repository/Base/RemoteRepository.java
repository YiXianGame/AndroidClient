package com.yixian.make.Repository.Base;

import android.util.Log;

import com.yixian.make.Core;
import com.yixian.make.RPC.Service.SkillCardService;
import com.yixian.make.RPC.Service.UserService;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Net.RPCNetConfig;
import com.yixian.material.EtherealC.Request.RPCNetRequestConfig;
import com.yixian.material.EtherealC.Service.RPCNetServiceConfig;
import com.yixian.material.Log.Log.Tag;
import com.yixian.make.RPC.Request.SkillCardRequest;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Net.RPCNetFactory;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Model.RPCType;
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
            type.add(Integer.class,"Int");
            type.add(String.class,"String");
            type.add(Boolean.class,"Bool");
            type.add(Long.class,"Long");
            type.add(SkillCard.class,"SkillCard");
            type.add(User.class,"User");
            type.add(CardGroup.class,"CardGroup");
            type.add(new ArrayList<Long>(){}.getClass().getGenericSuperclass(),"List<long>");
            type.add(new ArrayList<SkillCard>(){}.getClass().getGenericSuperclass(),"List<SkillCard>");
            type.add(new ArrayList<CardItem>(){}.getClass().getGenericSuperclass(),"List<CardItem>");
            type.add(new ArrayList<CardGroup>(){}.getClass().getGenericSuperclass(),"List<CardGroup>");
            type.add(new ArrayList<Friend>(){}.getClass().getGenericSuperclass(),"List<Friend>");
            type.add(new ArrayList<User>(){}.getClass().getGenericSuperclass(),"List<User>");
        }
        catch (RPCException e){
            Log.e(Tag.RemoteRepository,e.getMessage());
            e.printStackTrace();
        }
        RPCNetRequestConfig requestConfig = new RPCNetRequestConfig(type);
        RPCNetServiceConfig serviceConfig = new RPCNetServiceConfig(type);
        //每一个Service都可以拥有自己的TypeConvert.
        userRequest = RPCNetRequestFactory.register(UserRequest.class,"UserServer", Core.userServer.first,Core.userServer.second, requestConfig);
        skillCardRequest = RPCNetRequestFactory.register(SkillCardRequest.class,"SkillCardServer",Core.userServer.first,Core.userServer.second, requestConfig);
        RPCNetServiceFactory.register(new SkillCardService(),"SkillCardClient",Core.userServer.first,Core.userServer.second,serviceConfig);
        RPCNetServiceFactory.register(new UserService(),"UserClient",Core.userServer.first,Core.userServer.second,serviceConfig);
        RPCNetFactory.StartClient(Core.userServer.first,Core.userServer.second,new RPCNetConfig());
    }
}
