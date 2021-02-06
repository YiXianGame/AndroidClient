package com.xianyu.yixian_client.Model.Exception;

import com.xianyu.yixian_client.Model.RPC.Error;

public class RPCException extends Exception{
    public enum RpcErrorCode{Main};
    public RpcErrorCode Code = RpcErrorCode.Main;
    public RPCException(String message) {
        super(message);
    }
    public RPCException(String message,RpcErrorCode code) {
        super(message);
        Code = code;
    }
}
