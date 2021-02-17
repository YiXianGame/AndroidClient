package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.Buff;
import com.yixian.material.Utils.Utils;

import java.util.ArrayList;

public class BuffConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<Buff> buffs){
        return Utils.gson.toJson(buffs);
    }
    @TypeConverter
    public ArrayList<Buff> stringToArrayList(String buffs){
        return Utils.gson.fromJson(buffs, new TypeToken<ArrayList<Buff>>(){}.getType());
    }
}
