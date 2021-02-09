package com.yixian.material.RPC;

import com.google.gson.annotations.Expose;

public class Error {
    @Expose
    int Code;
    @Expose
    String Message;
    @Expose
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
