package com.yixian.material.EtherealC.Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.yixian.material.Log.Log.Tag;

import java.util.HashMap;

import kotlin.Triple;

public class RPCNetServiceFactory {
    //Java没有自带三元组，这里就引用Kotlin了.
    public static HashMap<Triple<String,String,String>, RPCNetService> services = new HashMap<>();

    public static void register(@NonNull Object instance, @NonNull String serviceName, @NonNull String hostname, @NonNull String port, @NonNull RPCNetServiceConfig config){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCNetService service = services.get(key);
        if(service == null){
            try{
                service = new RPCNetService();
                service.Register(instance,config.getType());
                services.put(key,service);
            }
            catch (Exception err){
                Log.e(Tag.RemoteRepository,serviceName + "异常报错，销毁注册\n" + err.getMessage());
            }
        }
    }
    public static void register(@NonNull Class instanceClass, @NonNull String serviceName, @NonNull String hostname, @NonNull String port, @NonNull RPCNetServiceConfig config){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        RPCNetService service = services.get(key);
        if(service == null){
            try{
                service = new RPCNetService();
                service.Register(instanceClass,config.getType());
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
    public static RPCNetService get(String serviceName, String hostname, String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        return services.get(key);
    }
}
