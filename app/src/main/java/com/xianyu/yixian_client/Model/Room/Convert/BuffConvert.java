package com.xianyu.yixian_client.Model.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;

import java.util.ArrayList;

public class BuffConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<Buff> buffs){
        return new Gson().toJson(buffs);
    }
    @TypeConverter
    public ArrayList<Buff> stringToArrayList(String buffs){
        return new Gson().fromJson(buffs, new TypeToken<ArrayList<Buff>>(){}.getType());
    }
}
