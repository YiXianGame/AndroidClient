package com.yixian.material.EtherealC.Request;

import android.util.Pair;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Annotation.RPCRequest;
import com.yixian.material.EtherealC.Model.ClientRequestModel;
import com.yixian.material.EtherealC.Model.ClientResponseModel;
import com.yixian.material.EtherealC.Net.RPCNetFactory;
import com.yixian.material.EtherealC.Net.Netty.SocketClient;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Random;

public  class RPCNetRequest implements InvocationHandler {
    private Random random = new Random();
    private String service;
    private Pair<String,String> clientKey;
    private RPCNetRequestConfig config;
    private int paramStart;
    public static <T> T register(Class<T> interface_class, String service, Pair<String,String> key, RPCNetRequestConfig config){
        RPCNetRequest proxy = new RPCNetRequest();
        proxy.service = service;
        proxy.clientKey = key;
        proxy.config = config;
        if (config.getTokenEnable()) proxy.paramStart = 1;
        else proxy.paramStart = 0;
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
            String[] array = new String[param_count + paramStart];
            if(annotation.parameters().length == 0){
                for(int i=0,j=paramStart;i<param_count;i++,j++){
                    String type_name;
                    cls = args[i].getClass();
                    type_name = config.getType().getAbstractName().get(cls);
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
                    for(int i=0,j=paramStart;i<args.length;i++,j++){
                        factType = config.getType().getAbstractType().get(types_name[i]);
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
            SocketClient socketClient = RPCNetFactory.GetClient(clientKey);
            Class<?> return_type = method.getReturnType();
            if(return_type.equals(Void.TYPE)){
                socketClient.SendVoid(request);
                return null;
            }
            else{
                socketClient.Send(request);
                ClientResponseModel respond = request.getResult();
                if(respond.getError()!=null){
                    if(respond.getError().getCode() == 0){
                        throw new RPCException(RPCException.RpcErrorCode.NoneAuthority,"用户权限不足");
                    }
                }
                return config.getType().getConvert().get(respond.getResultType()).convert(respond.getResult());
            }
        }
        else return method.invoke(this,args);
    }
}
