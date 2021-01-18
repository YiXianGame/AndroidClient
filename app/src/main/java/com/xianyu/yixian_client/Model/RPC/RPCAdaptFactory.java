package com.xianyu.yixian_client.Model.RPC;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.xianyu.yixian_client.Model.Log.Log.Tag;

import java.util.HashMap;

import kotlin.Triple;

public class RPCAdaptFactory {
    //Java没有自带三元组，这里就引用Kotlin了.
    public static HashMap<Triple<String,String,String>,RPCAdaptProxy> services = new HashMap<>();

    public static void Register(@NonNull Class classImp,@NonNull String serviceName,@NonNull String hostname,@NonNull String port,@NonNull RPCType type){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCAdaptProxy service = services.get(key);
        if(service == null){
            try{
                RPCClientFactory.GetClient(new Pair<>(hostname,port));
                service = new RPCAdaptProxy();
                service.Register(classImp,type);
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
