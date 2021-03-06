package com.yixian.material.EtherealC.Model;

import com.google.gson.annotations.Expose;

public class ClientResponseModel {
    @Expose
    private String JsonRpc = null;
    @Expose
    private String Result = null;
    @Expose
    private String ResultType = null;
    @Expose
    private Error Error = null;
    @Expose
    private String Id = null;

    public String getJsonRpc() {
        return JsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        JsonRpc = jsonRpc;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getResultType() {
        return ResultType;
    }

    public void setResultType(String resultType) {
        ResultType = resultType;
    }

    public com.yixian.material.EtherealC.Model.Error getError() {
        return Error;
    }

    public void setError(com.yixian.material.EtherealC.Model.Error error) {
        Error = error;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
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
