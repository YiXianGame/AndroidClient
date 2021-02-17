package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.History;
import com.yixian.material.Utils.Utils;

import java.util.ArrayList;

public class HistoryConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<History> histories){
        return Utils.gson.toJson(histories);
    }
    @TypeConverter
    public ArrayList<History> stringToArrayList(String histories){
        return Utils.gson.fromJson(histories, new TypeToken<ArrayList<History>>(){}.getType());
    }
}
