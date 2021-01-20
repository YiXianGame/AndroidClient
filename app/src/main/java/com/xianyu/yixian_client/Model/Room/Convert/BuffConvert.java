package com.xianyu.yixian_client.Model.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;

import java.util.ArrayList;
import java.util.HashMap;

public class BuffConvert {
    @TypeConverter
    public String arrayListToString(HashMap<Buff.Category,Buff> buffs){
        return new Gson().toJson(buffs);
    }
    @TypeConverter
    public HashMap<Buff.Category,Buff> stringToArrayList(String buffs){
        return new Gson().fromJson(buffs, new TypeToken<HashMap<Buff.Category,Buff>>(){}.getType());
    }
}
