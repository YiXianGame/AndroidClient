package com.xianyu.yixian_client.Model.RPC;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.lang.reflect.Type;
import java.util.HashMap;

public class RPCType {
    public interface IConvert {
        Object convert(Object obj);
    }
    private HashMap<Class<?>, String> typeToAbstract = new HashMap<>();
    private HashMap<String, Class<?>> abstractToType = new HashMap<>();
    private HashMap<String, IConvert> typeConvert = new HashMap<>();

    public HashMap<Class<?>, String> getTypeToAbstract() {
        return typeToAbstract;
    }

    public void setTypeToAbstract(HashMap<Class<?>, String> typeToAbstract) {
        this.typeToAbstract = typeToAbstract;
    }

    public HashMap<String, Class<?>> getAbstractToType() {
        return abstractToType;
    }

    public void setAbstractToType(HashMap<String, Class<?>> abstractToType) {
        this.abstractToType = abstractToType;
    }

    public HashMap<String, IConvert> getTypeConvert() {
        return typeConvert;
    }

    public void setTypeConvert(HashMap<String, IConvert> typeConvert) {
        this.typeConvert = typeConvert;
    }

    public void add(Class<?> type, String typeName) throws RPCException {
        if (typeConvert.containsKey(typeName) || typeToAbstract.containsKey(type) || abstractToType.containsKey(typeName)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type.getTypeName(),typeName));
        else{
            typeToAbstract.put(type, typeName);
            typeConvert.put(typeName, obj -> Core.gson.fromJson(Core.gson.toJsonTree(obj),type));
            abstractToType.put(typeName,type);
        }
    }
    public void add(Class<?> type,String typeName,IConvert convert) throws RPCException {
        if (typeConvert.containsKey(typeName) || typeToAbstract.containsKey(type) || abstractToType.containsKey(typeName)) throw new RPCException(String.format("类型:{1}转{2}发生异常,存在重复键",type.getTypeName(),typeName));
        else{
            typeToAbstract.put(type, typeName);
            typeConvert.put(typeName,convert);
            abstractToType.put(typeName,type);
        }
    }

}
