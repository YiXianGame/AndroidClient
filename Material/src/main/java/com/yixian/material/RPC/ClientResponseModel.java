package com.yixian.material.RPC;

import com.google.gson.annotations.Expose;

public class ClientResponseModel {
    @Expose
    public String JsonRpc = null;
    @Expose
    public String Result = null;
    @Expose
    public String ResultType = null;
    @Expose
    public Error Error = null;
    @Expose
    public String Id = null;

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
