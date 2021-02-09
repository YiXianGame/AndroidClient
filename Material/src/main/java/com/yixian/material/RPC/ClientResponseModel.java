package com.yixian.material.RPC;

import com.google.gson.annotations.Expose;

public class ClientResponseModel {
    @Expose
    public String JsonRpc = null;
    @Expose
    public Object Result = null;
    @Expose
    public Error Error = null;
    @Expose
    public String Id = null;

    public ClientResponseModel(String jsonRpc, Object result, Error error, String id) {
        JsonRpc = jsonRpc;
        Result = result;
        Error = error;
        Id = id;
    }

    @Override
    public String toString() {
        return "ClientResponseModel{" +
                "JsonRpc='" + JsonRpc + '\'' +
                ", Result=" + Result +
                ", Error=" + Error +
                ", Id='" + Id + '\'' +
                '}';
    }
}
