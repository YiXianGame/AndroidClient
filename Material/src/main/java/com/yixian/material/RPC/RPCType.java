package com.yixian.material.RPC;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RPCType {
    public interface IConvert {
        Object convert(Object obj);
    }
    private HashMap<Type, String> typeToAbstract = new HashMap<>();

    private HashMap<String, IConvert> typeConvert = new HashMap<>();

    public HashMap<Type, String> getTypeToAbstract() {
        return typeToAbstract;
    }

    public HashMap<String, IConvert> getTypeConvert() {
        return typeConvert;
    }


    public void add(Type type, String abstractName) throws RPCException {
        if (typeConvert.containsKey(abstractName) || typeToAbstract.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type, abstractName));
        else{
            typeToAbstract.put(type, abstractName);
            typeConvert.put(abstractName, obj -> Utils.gson.fromJson(Utils.gson.toJsonTree(obj),type));
        }
    }
    public void add(Type type, String abstractName, IConvert convert) throws RPCException {
        if (typeConvert.containsKey(abstractName) || typeToAbstract.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type, abstractName));
        else{
            typeToAbstract.put(type, abstractName);
            typeConvert.put(abstractName,convert);
        }
    }

}
