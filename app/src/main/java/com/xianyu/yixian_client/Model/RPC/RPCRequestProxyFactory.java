package com.xianyu.yixian_client.Model.RPC;

import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import android.util.Pair;

import androidx.annotation.NonNull;

import kotlin.Triple;

public class RPCRequestProxyFactory {
    static HashMap<Triple<String,String,String>,Object> services = new HashMap<>();
    public static <T> T Register(@NonNull Class<T> interface_class,@NonNull String serviceName,@NonNull String hostname,@NonNull String port,@NonNull RPCType type){
        T service = null;
        Triple<String,String,String> key = new Triple<String,String,String>(serviceName,hostname,port);
        service = (T)services.get(key);
        if(service == null){
            SocketClient socketClient = null;
            Pair<String,String> clientKey = new Pair<String,String>(hostname,port);
            try{
                socketClient = RPCClientFactory.GetClient(clientKey);
            }
            catch (Exception err){
                if(socketClient == null){
                    socketClient = RPCClientFactory.GetClient(clientKey);
                }
                else socketClient.doConnect();
            }
            service = RPCRequestProxy.Register(interface_class,serviceName,clientKey,type);
            services.put(key,service);
        }
        return service;
    }
    public static void Destory(String serviceName,String hostname,String port){
        Triple<String,String,String> key = new Triple<>(serviceName,hostname,port);
        if(services.containsKey(key)){
            services.remove(key);
            RPCClientFactory.Destory(new Pair<>(hostname,port));
        }
    }
}
