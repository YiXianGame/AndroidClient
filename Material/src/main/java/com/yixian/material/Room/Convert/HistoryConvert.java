package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.History;

import java.util.ArrayList;

public class HistoryConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<History> histories){
        return new Gson().toJson(histories);
    }
    @TypeConverter
    public ArrayList<History> stringToArrayList(String histories){
        return new Gson().fromJson(histories, new TypeToken<ArrayList<History>>(){}.getType());
    }
}
