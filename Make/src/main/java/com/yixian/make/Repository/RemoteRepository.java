package com.yixian.make.Repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.User;
import com.yixian.material.Log.Log.Tag;
import com.yixian.make.RPC.Request.SkillCardRequest;
import com.yixian.make.RPC.Server.SkillCardServer;
import com.yixian.make.RPC.Server.UserServer;
import com.yixian.material.RPC.RPCAdaptFactory;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.RPCRequestProxyFactory;
import com.yixian.material.RPC.RPCType;
import com.yixian.make.RPC.Request.UserRequest;
import com.yixian.make.Repository.Interface.IRemoteRepository;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Utils.Utils;

import java.text.DecimalFormat;
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
            //已经知道Gson将int转为了double,为空就默认加一个cast转换,不为空就是自定义Convert
            type.add(int.class,"int",obj -> Integer.parseInt(DecimalFormat.getIntegerInstance().format(obj)));
            type.add(String.class,"string");
            type.add(boolean.class,"bool");
            type.add(long.class,"long",obj -> Double.valueOf((double)obj).longValue());
            type.add(SkillCard.class,"skillCard");
            type.add(User.class,"user");
            type.add(new ArrayList<SkillCard>(){}.getClass().getGenericSuperclass(),"skillcards",value-> Utils.gson.fromJson((JsonElement) value,new TypeToken<ArrayList<SkillCard>>(){}.getType()));
        }
        catch (RPCException e){
            Log.e(Tag.RemoteRepository,e.getMessage());
            e.printStackTrace();
        }
        //每一个Service都可以拥有自己的TypeConvert.
        userRequest = RPCRequestProxyFactory.Register(UserRequest.class,"UserServer","192.168.0.105","28015",type);
        RPCAdaptFactory.Register(UserServer.class,"UserClient","192.168.0.105","28015",type);
        skillCardRequest = RPCRequestProxyFactory.Register(SkillCardRequest.class,"SkillCardServer","192.168.0.105","28015",type);
        RPCAdaptFactory.Register(SkillCardServer.class,"SkillCardClient","192.168.0.105","28015",type);
        //Observable.create((ObservableOnSubscribe<Void>) emitter -> Log.d(Tag.Debug,userDao.hello("你好"))).subscribeOn(Schedulers.io()).subscribe();
        skillCardRequest.syncUser2(new ArrayList<SkillCard>(){});
    }
}
