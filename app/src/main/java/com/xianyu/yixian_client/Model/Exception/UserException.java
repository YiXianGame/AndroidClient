package com.xianyu.yixian_client.Model.Exception;

public class UserException extends Exception{
    public enum UserErrorCode{Main,NotFoundUser};
    public UserErrorCode Code = UserErrorCode.Main;
    public UserException(String message) {
        super(message);
    }
    public UserException(String message, UserErrorCode code) {
        super(message);
        Code = code;
    }
}
