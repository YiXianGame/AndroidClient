package com.yixian.material.RPC;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.yixian.material.Utils.Utils;

import java.lang.reflect.Array;
import java.lang.reflect.Type;


public class ClientRequestModel {
    @Expose(serialize = false,deserialize = false)
    public ClientResponseModel Result;
    @Expose
    public String JsonRpc;
    @Expose
    public String MethodId;
    @Expose
    public String[] Params;
    @Expose
    public String Id;
    @Expose
    public String Service;


    public ClientRequestModel(String jsonRpc,String service, String methodId, String[] params) {
        JsonRpc = jsonRpc;
        MethodId = methodId;
        Params = params;
        Service = service;
    }

    public void setResult(ClientResponseModel result) {
        synchronized (this){
            Result = result;
            this.notify();
        }
    }

    public ClientResponseModel getResult()  {
        synchronized (this){
            if (Result == null){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return new ClientResponseModel();
                }
            }
            return Result;
        }
    }

    @Override
    public String toString() {
        return "ClientRequestModel{" +
                "JsonRpc='" + JsonRpc + '\'' +
                ", MethodId='" + MethodId + '\'' +
                ", Params=" + Utils.gson.toJson(Params) +
                ", Service='" + Service + '\'' +
                '}';
    }
}
