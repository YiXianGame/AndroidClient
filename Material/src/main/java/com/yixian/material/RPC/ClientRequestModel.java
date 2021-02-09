package com.yixian.material.RPC;

import com.google.gson.annotations.Expose;
import com.yixian.material.Utils.Utils;


public class ClientRequestModel {
    @Expose(serialize = false,deserialize = false)
    public Object Result;
    @Expose
    public String JsonRpc;
    @Expose
    public String MethodId;
    @Expose
    public Object[] Params;
    @Expose
    public String Id;
    @Expose
    public String Service;


    public ClientRequestModel(String jsonRpc,String service, String methodId, Object[] params) {
        JsonRpc = jsonRpc;
        MethodId = methodId;
        Params = params;
        Service = service;
    }

    public void setResult(Object result) {
        synchronized (this){
            Result = result;
            this.notify();
        }
    }

    public Object getResult() throws InterruptedException {
        synchronized (this){
            if (Result == null){
                this.wait();
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
