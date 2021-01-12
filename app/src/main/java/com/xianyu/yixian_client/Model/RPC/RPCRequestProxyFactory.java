package com.xianyu.yixian_client.Model.RPC;

import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.util.concurrent.ConcurrentHashMap;

import android.util.Pair;
import kotlin.Triple;

public class RPCRequestProxyFactory {
    static ConcurrentHashMap<Triple<String,String,String>,Object> services = new ConcurrentHashMap<>();
    public static <T> T Register(Class<T> classImp,String serviceName,String hostname,String port){
        T service = null;
        Triple<String,String,String> key = new Triple<String,String,String>(serviceName,hostname,port);
        service = (T)services.get(key);
        if(service == null){
            SocketClient socketClient = null;
            Pair<String,String> clientKey = new Pair<>(hostname,port);
            try{
                socketClient = RPCClientFactory.GetClient(clientKey);
                service = RPCRequestProxy.Create(classImp,serviceName,clientKey);
                services.put(key,service);
            }
            catch (Exception err){
                if(socketClient == null){
                    socketClient = RPCClientFactory.GetClient(clientKey);
                }
                else socketClient.doConnect();
            }
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
