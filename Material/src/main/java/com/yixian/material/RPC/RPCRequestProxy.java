package com.yixian.material.RPC;

import android.util.Log;
import android.util.Pair;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.Log.Log.Tag;
import com.yixian.material.RPC.Annotation.RPCMethod;
import com.yixian.material.Tcp.SocketClient;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
            Type factType;
            Class<?> cls;
            String[] array = new String[args.length+1];
            RPCMethod annotation = method.getAnnotation(RPCMethod.class);
            if(annotation != null){
                String[] types_name = annotation.parameters().split("-");
                if(args.length == types_name.length){
                    for(int i=0,j=1;i<args.length;i++,j++){
                        factType = type.getAbstractType().get(types_name[i]);
                        if(factType!=null){
                            methodId.append("-" + types_name[i]);
                            array[j] = Utils.gson.toJson(args[i],factType);
                        }
                        else throw new RPCException(String.format("方法体%s中的抽象类型为%s的类型尚未注册！",method.getName(),types_name[i]));
                    }
                }
                else throw new RPCException(String.format("方法体%s中RPCMethod注解与实际参数数量不符,@RPCMethod:%d个,Method:%d个",method.getName(),types_name.length,args.length));
            }
            else {
                if(args.length == method.getParameterCount()){
                    for(int i=0,j=1;i<args.length;i++,j++){
                        String type_name;
                        cls = args[i].getClass();
                        type_name = type.getAbstractName().get(cls);
                        if(type_name != null) {
                            methodId.append("-").append(type_name);
                            array[j] = Utils.gson.toJson(args[i],cls);
                        }
                        else throw new RPCException(String.format("Java中的%s类型参数尚未注册！", args[i].getClass().getName()));
                    }
                }
                else throw new RPCException(String.format("方法体%s中RPCMethod注解与实际参数数量不符,@RPCMethod:%d个,Method:%d个",method.getName(),method.getParameterCount(),args.length));
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
                if(respond.ResultType.equals("null"))return null;
                else return type.getConvert().get(respond.ResultType).convert(respond.Result);
            }
        }
        catch (Exception err){
            Log.d(Tag.RemoteRepository,err.getMessage());
            err.printStackTrace();
        }
        return null;
    }
}
