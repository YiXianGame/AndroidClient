package com.xianyu.yixian_client.Model.RPC;
public class ClientResponseModel {
    public String JsonRpc = null;
    public Object Result = null;
    public Error Error = null;
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
