package com.xianyu.yixian_client.Model.RPC;

import android.util.Pair;

import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public  class RPCRequestProxy implements InvocationHandler {
    private Random random = new Random();
    private String service;
    private Pair<String,String> clientkey;
    public static  <T> T Create(Class<T> T,String service,Pair<String,String> key){
        RPCRequestProxy proxy = new RPCRequestProxy();
        proxy.service = service;
        proxy.clientkey = key;
        return (T) Proxy.newProxyInstance(T.getClass().getClassLoader(), T.getClass().getInterfaces(), proxy);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            StringBuilder methodId = new StringBuilder(method.getName());
            for(int i=0;i<args.length;i++){
                methodId.append("-" + args.getClass().getTypeName());
                Object[] obj = new Object[args.length + 1];
                Arrays.fill(obj,1,args.length,args);
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
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return null;
    }
}
