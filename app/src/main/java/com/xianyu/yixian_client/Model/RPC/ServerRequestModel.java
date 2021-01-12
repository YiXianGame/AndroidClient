package com.xianyu.yixian_client.Model.RPC;

import com.xianyu.yixian_client.Core;

import java.util.Arrays;

public class ServerRequestModel {
    public String JsonRpc;
    public String MethodId;
    public Object[] Params;
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
                ", Params=" + Core.gson.toJson(Params) +
                ", Service='" + Service + '\'' +
                '}';
    }
}
