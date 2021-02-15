package com.yixian.material.Exception;

public class SkillCardException extends Exception{
    public enum SkillCardErrorCode{SyncError,SyncUserError};
    public SkillCardErrorCode Code = SkillCardErrorCode.SyncError;
    public SkillCardException(String message) {
        super(message);
    }
    public SkillCardException(SkillCardErrorCode code, String message) {
        super(message);
        Code = code;
    }
}
