package com.xianyu.yixian_client.Model.RPC;

import android.util.Pair;

import java.util.HashMap;

public class RPCType {
    public interface IConvert {
        Object convert(Object obj);
    }
    private HashMap<Class, String> typeToAbstract = new HashMap<>();
    private HashMap<String, IConvert> abstractToType = new HashMap<>();
    public HashMap<Class, String> getTypeToAbstract() {
        return typeToAbstract;
    }

    public void setTypeToAbstract(HashMap<Class, String> typeToAbstract) {
        this.typeToAbstract = typeToAbstract;
    }

    public HashMap<String, IConvert> getAbstractToType() {
        return abstractToType;
    }

    public void setAbstractToType(HashMap<String, IConvert> abstractToType) {
        this.abstractToType = abstractToType;
    }

    public void add(Class<?> type,String typeName) throws RPCException {
        if (abstractToType.containsKey(typeName) || typeToAbstract.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type.getTypeName(),typeName));
        else{
            typeToAbstract.put(type, typeName);
            abstractToType.put(typeName, type::cast);
        }
    }
    public void add(Class<?> type,String typeName,IConvert convert) throws RPCException {
        if (abstractToType.containsKey(typeName) || typeToAbstract.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type.getTypeName(),typeName));
        else{
            typeToAbstract.put(type, typeName);
            abstractToType.put(typeName,convert);
        }
    }
}
