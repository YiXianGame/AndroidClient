package com.yixian.material.Exception;

public class UserException extends Exception{
    public enum UserErrorCode{Main,NotFoundUser};
    public UserErrorCode Code = UserErrorCode.Main;
    public UserException(String message) {
        super(message);
    }
    public UserException(UserErrorCode code,String message) {
        super(message);
        Code = code;
    }
}
