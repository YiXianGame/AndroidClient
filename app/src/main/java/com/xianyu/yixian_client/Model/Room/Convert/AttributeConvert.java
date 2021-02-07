package com.xianyu.yixian_client.Model.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class AttributeConvert {
    @TypeConverter
    public String arrayListToString(HashMap<Attribute.Category,Attribute> attributes){
        return new Gson().toJson(attributes);
    }
    @TypeConverter
    public HashMap<Attribute.Category,Attribute> stringToArrayList(String attributes){
        return new Gson().fromJson(attributes, new TypeToken<HashMap<Attribute.Category,Attribute>>(){}.getType());
    }
}
