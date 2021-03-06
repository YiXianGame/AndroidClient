package com.yixian.material.EtherealC.Model;

import com.google.gson.annotations.Expose;
import com.yixian.material.Utils.Utils;


public class ClientRequestModel {
    @Expose(serialize = false,deserialize = false)
    private ClientResponseModel Result;
    @Expose
    private String JsonRpc;
    @Expose
    private String MethodId;
    @Expose
    private String[] Params;
    @Expose
    private String Id;
    @Expose
    private String Service;

    public String getJsonRpc() {
        return JsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        JsonRpc = jsonRpc;
    }

    public String getMethodId() {
        return MethodId;
    }

    public void setMethodId(String methodId) {
        MethodId = methodId;
    }

    public String[] getParams() {
        return Params;
    }

    public void setParams(String[] params) {
        Params = params;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public ClientRequestModel(String jsonRpc, String service, String methodId, String[] params) {
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
