package com.yixian.material.Exception;

public class RPCException extends Exception{
    public enum RpcErrorCode{Main};
    public RpcErrorCode Code = RpcErrorCode.Main;
    public RPCException(String message) {
        super(message);
    }
    public RPCException(RpcErrorCode code,String message) {
        super(message);
        Code = code;
    }
}
