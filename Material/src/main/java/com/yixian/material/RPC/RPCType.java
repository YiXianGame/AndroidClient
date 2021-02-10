package com.yixian.material.RPC;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RPCType {
    public interface IConvert {
        Object convert(String obj);
    }
    private HashMap<Type, String> abstractName = new HashMap<>();
    private HashMap<String, IConvert> convert = new HashMap<>();

    public HashMap<Type, String> getAbstractName() {
        return abstractName;
    }

    public void setAbstractName(HashMap<Type, String> abstractName) {
        this.abstractName = abstractName;
    }

    public HashMap<String, IConvert> getConvert() {
        return convert;
    }

    public void setConvert(HashMap<String, IConvert> convert) {
        this.convert = convert;
    }

    public void add(Type type, String abstractName) throws RPCException {
        if (convert.containsKey(abstractName) || this.abstractName.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type, abstractName));
        else{
            this.abstractName.put(type, abstractName);
            convert.put(abstractName, obj -> Utils.gson.fromJson(obj,type));
        }
    }
    public void add(Type type, String abstractName, IConvert convert) throws RPCException {
        if (this.convert.containsKey(abstractName) || this.abstractName.containsKey(type)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type, abstractName));
        else{
            this.abstractName.put(type, abstractName);
            this.convert.put(abstractName,convert);
        }
    }
}
