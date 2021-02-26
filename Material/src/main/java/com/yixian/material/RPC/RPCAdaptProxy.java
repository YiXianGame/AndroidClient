package com.yixian.material.RPC;

import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class RPCAdaptProxy {
    private HashMap<String,Method> methods = new HashMap<>();
    private RPCType type;
    private Object instance = null;

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public RPCType getType() {
        return type;
    }

    public void setType(RPCType type) {
        this.type = type;
    }

    public HashMap<String, Method> getMethods() {
        return methods;
    }
    public void setMethods(HashMap<String, Method> methods) {
        this.methods = methods;
    }

    public void Register(Object instance,RPCType type) throws RPCException {
        this.instance = instance;
        Register(instance.getClass(),type);
    }
    public void Register(Class instanceClass,RPCType type) throws RPCException {
        StringBuilder methodId = new StringBuilder();
        this.type = type;
        for(Method method : instance.getClass().getMethods())
        {
            int modifier = method.getModifiers();
            RPCMethod annotation = method.getAnnotation(RPCMethod.class);
            if(annotation!=null){
                if(!Modifier.isInterface(modifier)){
                    methodId.append(method.getName());
                    if(!annotation.parameters().equals("")){
                        String type_name;
                        for(Class<?> parameter_type : method.getParameterTypes()){
                            type_name = type.getAbstractName().get(parameter_type);
                            if(type_name != null) {
                                methodId.append("-").append(type_name);
                            }
                            else throw new RPCException(String.format("Java中的%%s类型参数尚未注册,请注意是否是泛型导致！",parameter_type.getName()));
                        }
                    }
                    else {
                        String[] types_name = annotation.parameters().split("-");
                        for(String type_name : types_name){
                            if(type.getAbstractType().containsKey(type_name)){
                                methodId.append("-").append(type_name);
                            }
                            else throw new RPCException(String.format("Java中的%s抽象类型参数尚未注册,请注意是否是泛型导致！",type_name));
                        }
                        methodId.append("-").append(annotation.parameters());
                    }
                    methods.put(methodId.toString(),method);
                    methodId.setLength(0);
                }
            }
        }
    }
    public void ConvertParams(String methodId, Object[] parameters) throws RPCException {
        //正则可能慢了一点,但是是客户端,基本忽略了.
        String[] param_id = methodId.split("-");
        for (int i = 1,j=0; i < param_id.length; i++,j++)
        {
            if(type.getConvert().get(param_id[i]) == null)throw new RPCException(String.format("RPC中的%s类型参数尚未被注册！",param_id[i]));
            else parameters[j] = (type.getConvert().get(param_id[i])).convert((String) parameters[j]);
        }
    }
}
