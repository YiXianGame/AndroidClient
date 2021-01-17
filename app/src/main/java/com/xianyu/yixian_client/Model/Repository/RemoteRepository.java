package com.xianyu.yixian_client.Model.Repository;

import android.util.Log;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.RPC.Adapt.Command;
import com.xianyu.yixian_client.Model.RPC.RPCAdaptFactory;
import com.xianyu.yixian_client.Model.RPC.RPCException;
import com.xianyu.yixian_client.Model.RPC.RPCRequestProxyFactory;
import com.xianyu.yixian_client.Model.RPC.RPCType;
import com.xianyu.yixian_client.Model.RPC.Request.UserDao;

import java.text.DecimalFormat;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

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
public class RemoteRepository {
    public UserDao userDao;
    public RemoteRepository(){
        boolean test = true;
        if(test){
            RPCType type = new RPCType();
            try{
                //已经知道Gson将int转为了double,为空就默认加一个cast转换,不为空就是自定义Convert
                type.add(int.class,"int",obj -> Integer.parseInt(DecimalFormat.getIntegerInstance().format(obj)));
                type.add(String.class,"string");
                type.add(boolean.class,"bool");
            }
            catch (RPCException e){
                Log.e(Tag.RemoteRepository,e.getMessage());
                e.printStackTrace();
            }
            //每一个Service都可以拥有自己的TypeConvert.
            userDao = RPCRequestProxyFactory.Register(UserDao.class,"User","192.168.0.105","28015",type);
            RPCAdaptFactory.Register(Command.class,"Command","192.168.0.105","28015",type);
            Observable.create((ObservableOnSubscribe<Void>) emitter -> Log.d(Tag.Debug,userDao.hello("你好"))).subscribeOn(Schedulers.io()).subscribe();
        }
    }
}
