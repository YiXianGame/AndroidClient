package com.xianyu.yixian_client.Model.RPC;

import android.util.Pair;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Random;

public  class RPCRequestProxy implements InvocationHandler {
    private Random random = new Random();
    private String service;
    private Pair<String,String> clientKey;
    private HashMap<Class,String> typeToAbstract;
    public static <T> T Register(Class<T> interface_class,String service,Pair<String,String> key,RPCType type){
        RPCRequestProxy proxy = new RPCRequestProxy();
        proxy.service = service;
        proxy.clientKey = key;
        proxy.typeToAbstract = type.getTypeToAbstract();
        return (T) Proxy.newProxyInstance(interface_class.getClassLoader(),new Class<?>[]{interface_class}, proxy);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try{
            StringBuilder methodId = new StringBuilder(method.getName());
            String type_name;
            for(Type param_type : method.getParameterTypes()){
                type_name = typeToAbstract.get(param_type);
                if(type_name != null) {
                    methodId.append("-" + type_name);
                }
                else throw new RPCException(String.format("Java中的%s类型参数尚未注册！", type_name));
            }
            Object[] obj = new Object[args.length + 1];
            for(int i=1;i < obj.length;i++){
                obj[i] = args[i - 1];
            }
            String a = Core.gson.toJson(args);
            ClientRequestModel request = new ClientRequestModel("2.0", service, methodId.toString(), obj);
            SocketClient socketClient = RPCClientFactory.GetClient(clientKey);
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
