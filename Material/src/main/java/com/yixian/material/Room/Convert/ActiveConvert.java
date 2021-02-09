package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.yixian.material.Entity.User;

public class ActiveConvert {
    @TypeConverter
    public String enumToString(User.State value){
        return value.toString();
    }
    @TypeConverter
    public User.State stringToEnum(String value){
        return User.State.valueOf(User.State.class,value);
    }
}
