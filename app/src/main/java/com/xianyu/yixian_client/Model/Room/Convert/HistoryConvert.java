package com.xianyu.yixian_client.Model.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianyu.yixian_client.Model.Room.Entity.History;

import java.util.ArrayList;

public class HistoryConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<History> buffs){
        return new Gson().toJson(buffs);
    }
    @TypeConverter
    public ArrayList<History> stringToArrayList(String buffs){
        return new Gson().fromJson(buffs, new TypeToken<ArrayList<History>>(){}.getType());
    }
}
