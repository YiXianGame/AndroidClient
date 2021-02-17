package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Utils.Utils;

import java.util.ArrayList;

public class GroupConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<CardGroup> groups){
        return  Utils.gson.toJson(groups);
    }
    @TypeConverter
    public ArrayList<CardGroup> stringToArrayList(String groups){
        return Utils.gson.fromJson(groups, new TypeToken<ArrayList<CardGroup>>(){}.getType());
    }
}
