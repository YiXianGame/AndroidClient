package com.xianyu.yixian_client.Model.RPC;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.concurrent.ConcurrentHashMap;

public class RPCAdaptProxy {
    public ConcurrentHashMap<String,Method> methods = new ConcurrentHashMap<>();
    public <T> void Register(Class<T> adaptImp)
    {
        StringBuilder methodId = new StringBuilder();
        for(Method method : adaptImp.getMethods())
        {
            int modifier = method.getModifiers();
            if(Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && !Modifier.isInterface(modifier)){
                methodId.append(method.getName());
                for(Parameter parameter : method.getParameters()){
                    methodId.append("-" + parameter.getName());
                }
                methods.put(methodId.toString(),method);
                methodId.setLength(0);
            }
        }
    }
}
