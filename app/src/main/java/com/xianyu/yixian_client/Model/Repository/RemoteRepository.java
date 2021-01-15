package com.xianyu.yixian_client.Model.Repository;

import com.xianyu.yixian_client.Model.RPC.RPCRequestProxyFactory;
import com.xianyu.yixian_client.Model.RPC.Request.IUser;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
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
    public RemoteRepository(){
        boolean remote = false;
        if(remote){
            IUser user = RPCRequestProxyFactory.Register(IUser.class,"User","192.168.0.105","28015");
            Observable.create((ObservableOnSubscribe<Void>) emitter -> user.hello("你好")).subscribeOn(Schedulers.io()).subscribe();
        }
    }
}
