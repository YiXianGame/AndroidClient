package com.xianyu.yixian_client.Model.RPC;

public class Error {
    int Code;
    String Message;
    String Data;

    @Override
    public String toString() {
        return "Error{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                ", Data='" + Data + '\'' +
                '}';
    }
}
