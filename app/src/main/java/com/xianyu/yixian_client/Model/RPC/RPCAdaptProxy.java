package com.xianyu.yixian_client.Model.RPC;

import com.xianyu.yixian_client.Model.Exception.RPCException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class RPCAdaptProxy {
    private HashMap<String,Method> methods = new HashMap<>();
    private RPCType type;
    public HashMap<String, Method> getMethods() {
        return methods;
    }
    public void setMethods(HashMap<String, Method> methods) {
        this.methods = methods;
    }

    public <T> void Register(Class<T> adaptImp,RPCType type) throws RPCException {
        StringBuilder methodId = new StringBuilder();
        this.type = type;
        for(Method method : adaptImp.getMethods())
        {
            int modifier = method.getModifiers();
            if(Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && !Modifier.isInterface(modifier)){
                methodId.append(method.getName());
                String type_name;
                for(Class parameter_type : method.getParameterTypes()){
                    type_name = type.getTypeToAbstract().get(parameter_type);
                    if(type_name != null) {
                        methodId.append("-").append(type_name);
                    }
                    else throw new RPCException(String.format("Java中的%s类型参数尚未注册！",type_name));
                }
                methods.put(methodId.toString(),method);
                methodId.setLength(0);
            }
        }
    }
    public void ConvertParams(String methodId, Object[] parameters) throws RPCException {
        //正则可能慢了一点,但是是客户端,基本忽略了.
        String[] param_id = methodId.split("-");
        for (int i = 1,j=0; i < param_id.length; i++,j++)
        {
            if(type.getTypeConvert().get(param_id[i]) == null)throw new RPCException(String.format("RPC中的%s类型参数尚未被注册！",param_id[i]));
            else parameters[j] = (type.getTypeConvert().get(param_id[i])).convert(parameters[j]);
        }
    }
}
