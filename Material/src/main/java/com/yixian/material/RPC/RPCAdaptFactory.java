package com.yixian.material.RPC;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.yixian.material.Log.Log.Tag;

import java.util.HashMap;

import kotlin.Triple;

public class RPCAdaptFactory {
    //Java没有自带三元组，这里就引用Kotlin了.
    public static HashMap<Triple<String,String,String>, RPCAdaptProxy> services = new HashMap<>();

    public static void register(@NonNull Object instance, @NonNull String serviceName, @NonNull String hostname, @NonNull String port, @NonNull RPCType type){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCAdaptProxy service = services.get(key);
        if(service == null){
            try{
                service = new RPCAdaptProxy();
                service.Register(instance,type);
                services.put(key,service);
            }
            catch (Exception err){
                Log.e(Tag.RemoteRepository,serviceName + "异常报错，销毁注册\n" + err.getMessage());
            }
        }
    }
    public static void register(@NonNull Class instanceClass, @NonNull String serviceName, @NonNull String hostname, @NonNull String port, @NonNull RPCType type){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCAdaptProxy service = services.get(key);
        if(service == null){
            try{
                service = new RPCAdaptProxy();
                service.Register(instanceClass,type);
                services.put(key,service);
            }
            catch (Exception err){
                Log.e(Tag.RemoteRepository,serviceName + "异常报错，销毁注册\n" + err.getMessage());
            }
        }
    }
    public static void destory(String serviceName, String hostname, String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        if(services.containsKey(key)){
            services.remove(key);
        }
    }
    public static RPCAdaptProxy get(String serviceName, String hostname, String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        return services.get(key);
    }
}
