package com.yixian.material.RPC;

import android.util.Pair;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.Annotation.RPCRequest;
import com.yixian.material.Tcp.SocketClient;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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
    public Object invoke(Object proxy, Method method, Object[] args) throws RPCException, InvocationTargetException, IllegalAccessException {
        RPCRequest annotation = method.getAnnotation(RPCRequest.class);
        if(annotation != null){
            StringBuilder methodId = new StringBuilder(method.getName());
            Type factType;
            Class<?> cls;
            int param_count;
            if(args!=null)param_count = args.length;
            else param_count = 0;
            String[] array = new String[param_count+1];
            if(annotation.parameters().length == 0){
                for(int i=0,j=1;i<param_count;i++,j++){
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
            else {
                String[] types_name = annotation.parameters();
                if(param_count == types_name.length){
                    for(int i=0,j=1;i<args.length;i++,j++){
                        factType = type.getAbstractType().get(types_name[i]);
                        if(factType!=null){
                            methodId.append("-").append(types_name[i]);
                            array[j] = Utils.gson.toJson(args[i],factType);
                        }
                        else throw new RPCException(String.format("方法体%s中的抽象类型为%s的类型尚未注册！",method.getName(),types_name[i]));
                    }
                }
                else throw new RPCException(String.format("方法体%s中RPCMethod注解与实际参数数量不符,@RPCRequest:%d个,Method:%d个",method.getName(),types_name.length,args.length));
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
        else return method.invoke(this,args);
    }
}
