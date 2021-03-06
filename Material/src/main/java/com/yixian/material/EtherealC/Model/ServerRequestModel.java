package com.yixian.material.EtherealC.Model;

import com.google.gson.annotations.Expose;
import com.yixian.material.Utils.Utils;

public class ServerRequestModel {
    @Expose
    public String JsonRpc;
    @Expose
    public String MethodId;
    @Expose
    public Object[] Params;
    @Expose
    public String Service;

    public ServerRequestModel(String jsonRpc, String methodId, Object[] params, String service) {
        JsonRpc = jsonRpc;
        MethodId = methodId;
        Params = params;
        Service = service;
    }

    @Override
    public String toString() {
        return "ServerRequestModel{" +
                "JsonRpc='" + JsonRpc + '\'' +
                ", MethodId='" + MethodId + '\'' +
                ", Params=" + Utils.gson.toJson(Params) +
                ", Service='" + Service + '\'' +
                '}';
    }
}
