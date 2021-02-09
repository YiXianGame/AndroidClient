package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;

public class AttributeConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<SkillCard.Category> attributes){
        return new Gson().toJson(attributes);
    }
    @TypeConverter
    public ArrayList<SkillCard.Category> stringToArrayList(String attributes){
        return new Gson().fromJson(attributes, new TypeToken<ArrayList<SkillCard.Category>>(){}.getType());
    }
}
