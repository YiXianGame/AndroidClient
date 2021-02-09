package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.CardGroup;

import java.util.ArrayList;

public class GroupConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<CardGroup> buffs){
        return new Gson().toJson(buffs);
    }
    @TypeConverter
    public ArrayList<CardGroup> stringToArrayList(String buffs){
        return new Gson().fromJson(buffs, new TypeToken<ArrayList<CardGroup>>(){}.getType());
    }
}
