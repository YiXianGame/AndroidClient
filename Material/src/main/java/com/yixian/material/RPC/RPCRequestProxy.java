package com.yixian.material.RPC;

import android.util.Log;
import android.util.Pair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.Log.Log.Tag;
import com.yixian.material.Tcp.SocketClient;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public  class RPCRequestProxy implements InvocationHandler {
    private Random random = new Random();
    private String service;
    private Pair<String,String> clientKey;
    private RPCType type;
    public static <T> T Register(Class<T> interface_class,String service,Pair<String,String> key,RPCType type){
        RPCRequestProxy proxy = new RPCRequestProxy();
        proxy.service = service;
        proxy.clientKey = key;
        proxy.type = type;
        return (T) Proxy.newProxyInstance(interface_class.getClassLoader(),new Class<?>[]{interface_class}, proxy);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try{
            StringBuilder methodId = new StringBuilder(method.getName());
            String type_name;
            Type factType;
            Class<?> cls;
            String[] array = new String[args.length+1];
            for(int i=0,j=1;i<args.length;i++,j++){
                cls = args[i].getClass();
                type_name = type.getAbstractName().get(cls);
                if(type_name != null) {
                    methodId.append("-").append(type_name);
                    array[j] = Utils.gson.toJson(args[i],cls);
                }
                else {
                    factType = cls.getGenericSuperclass();
                    type_name = type.getAbstractName().get(factType);
                    if(type_name != null){
                        methodId.append("-").append(type_name);
                        array[j] = Utils.gson.toJson(args[i],factType);
                    }
                    else throw new RPCException(String.format("Java中的%s类型参数尚未注册！", args[i].getClass().getName()+"||"+ args[i].getClass().getGenericSuperclass().getTypeName()));
                }
            }
            ClientRequestModel request = new ClientRequestModel("2.0", service, methodId.toString(),array);

            SocketClient socketClient = RPCClientFactory.GetClient(clientKey);
            Class<?> return_type = method.getReturnType();
            if(return_type.equals(Void.TYPE)){
                socketClient.SendVoid(request);
                return null;
            }
            else{
                socketClient.Send(request);
                ClientResponseModel respond = request.getResult();
                return type.getConvert().get(respond.ResultType).convert((String) respond.Result);
            }
        }
        catch (Exception err){
            Log.d(Tag.RemoteRepository,err.getMessage());
            err.printStackTrace();
        }
        return null;
    }
}
