package com.yixian.material.RPC;

import android.util.Log;
import android.util.Pair;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.Log.Log.Tag;
import com.yixian.material.Tcp.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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
            for(Object param_type : args){
                cls = param_type.getClass();
                type_name = type.getTypeToAbstract().get(cls);
                if(type_name != null) methodId.append("-").append(type_name);
                else {
                    type_name = type.getTypeToAbstract().get(cls.getGenericSuperclass());
                    if(type_name != null)methodId.append("-").append(type_name);
                    else throw new RPCException(String.format("Java中的%s类型参数尚未注册！", param_type.getClass().getName()+"||"+param_type.getClass().getGenericSuperclass().getTypeName()));
                }
            }
            Object[] obj;
            obj = new Object[args.length + 1];
            System.arraycopy(args, 0, obj, 1, obj.length - 1);
            ClientRequestModel request = new ClientRequestModel("2.0", service, methodId.toString(), obj);
            SocketClient socketClient = RPCClientFactory.GetClient(clientKey);
            Class<?> return_type = method.getReturnType();
            if(return_type.equals(Void.TYPE)){
                socketClient.SendVoid(request);
                return null;
            }
            else{
                socketClient.Send(request);
                Object result = request.getResult();
                if(type.getTypeToAbstract().containsKey(return_type)){
                    if(type.getTypeConvert().containsKey(type.getTypeToAbstract().get(return_type))){
                        return type.getTypeConvert().get(type.getTypeToAbstract().get(return_type)).convert(result);
                    }
                    else throw new RPCException(String.format("Java中的%s类型的转换器尚未注册！", return_type));
                }
                else throw new RPCException(String.format("Java中的%s类型的参数尚未注册！", return_type));
            }
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return null;
    }
}
