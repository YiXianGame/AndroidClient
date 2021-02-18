package com.yixian.material.RPC;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.yixian.material.Log.Log.Tag;

import java.util.HashMap;

import kotlin.Triple;

public class RPCServerFactory {
    //Java没有自带三元组，这里就引用Kotlin了.
    public static HashMap<Triple<String,String,String>, RPCServerProxy> services = new HashMap<>();

    public static void Register(@NonNull Class classImp,@NonNull String serviceName,@NonNull String hostname,@NonNull String port,@NonNull RPCType type){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCServerProxy service = services.get(key);
        if(service == null){
            try{
                RPCClientFactory.GetClient(new Pair<>(hostname,port));
                service = new RPCServerProxy();
                service.Register(classImp,type);
                services.put(key,service);
            }
            catch (Exception err){
                Log.e(Tag.RemoteRepository,serviceName + "异常报错，销毁注册\n" + err.getMessage());

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