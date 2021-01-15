package com.xianyu.yixian_client.Model.RPC;

import android.util.Pair;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Observer;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public  class RPCRequestProxy implements InvocationHandler {
    private Random random = new Random();
    private String service;
    private Pair<String,String> clientkey;
    public static <T> T Create(Class<T> interface_class,String service,Pair<String,String> key){
        RPCRequestProxy proxy = new RPCRequestProxy();
        proxy.service = service;
        proxy.clientkey = key;
        return (T) Proxy.newProxyInstance(interface_class.getClassLoader(),new Class<?>[]{interface_class}, proxy);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try{
            StringBuilder methodId = new StringBuilder(method.getName());
            for(Object param : args){
                methodId.append("-" + param.getClass().getSimpleName());
            }
            Object[] obj = new Object[args.length + 1];
            for(int i=1;i < args.length;i++){
                obj[i] = args[i - 1];
            }
            String a = Core.gson.toJson(args);
            Arrays.fill(obj,1,obj.length,args);
            ClientRequestModel request = new ClientRequestModel("2.0", service, methodId.toString(), obj);
            SocketClient socketClient = RPCClientFactory.GetClient(clientkey);
            if(method.getReturnType() == Void.TYPE){
                socketClient.SendVoid(request);
                return null;
            }
            else {
                socketClient.Send(request);
                return request.getResult();
            }
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return null;
    }
}
