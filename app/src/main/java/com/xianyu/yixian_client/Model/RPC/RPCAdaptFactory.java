package com.xianyu.yixian_client.Model.RPC;

import android.os.Debug;
import android.util.Log;
import android.util.Pair;

import com.xianyu.yixian_client.Model.Log.Log.Tag;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import kotlin.Triple;
import kotlin.TuplesKt;

public class RPCAdaptFactory {
    //Java没有自带三元组，这里就引用Kotlin了.
    public static ConcurrentHashMap<Triple<String,String,String>,RPCAdaptProxy> services = new ConcurrentHashMap<>();
    public static void Register(Class classImp,String serviceName,String hostname,String port){
        RPCAdaptProxy service = null;
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        service = services.get(key);
        if(service == null){
            try{
                RPCClientFactory.GetClient(new Pair<>(hostname,port));
                service = new RPCAdaptProxy();
                service.Register(classImp);
                services.put(key,service);
            }
            catch (Exception err){
                Log.e(Tag.RemoteRepository,"发送异常报错，销毁注册");

            }
        }
    }
    public static void Destory(String serviceName,String hostname,String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        if(services.containsKey(key)){
            services.remove(key);
            RPCClientFactory.Destory(new Pair<>(hostname,port));
        }
    }
}
