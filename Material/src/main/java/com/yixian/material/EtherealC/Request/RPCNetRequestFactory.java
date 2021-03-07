package com.yixian.material.EtherealC.Request;

import com.yixian.material.EtherealC.Net.Netty.SocketClient;

import java.util.HashMap;

import android.util.Pair;

import androidx.annotation.NonNull;

import kotlin.Triple;

public class RPCNetRequestFactory {
    static HashMap<Triple<String,String,String>,Object> services = new HashMap<>();

    public static <T> T register(@NonNull Class<T> interface_class, @NonNull String serviceName, @NonNull String hostname, @NonNull String port, @NonNull RPCNetRequestConfig config){
        T service = null;
        Triple<String,String,String> key = new Triple<String,String,String>(serviceName,hostname,port);
        service = (T)services.get(key);
        if(service == null){
            SocketClient socketClient = null;
            Pair<String,String> clientKey = new Pair<String,String>(hostname,port);
            service = RPCNetRequest.register(interface_class,serviceName,clientKey,config);
            services.put(key,service);
        }
        return service;
    }

    public static void destory(String serviceName, String hostname, String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        if(services.containsKey(key)){
            services.remove(key);
        }
    }

}
