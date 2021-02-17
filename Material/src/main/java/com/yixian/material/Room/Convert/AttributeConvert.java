package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Utils.Utils;

import java.util.ArrayList;

public class AttributeConvert {
    @TypeConverter
    public String arrayListToString(ArrayList<SkillCard.Category> attributes){
        return Utils.gson.toJson(attributes);
    }
    @TypeConverter
    public ArrayList<SkillCard.Category> stringToArrayList(String attributes){
        return Utils.gson.fromJson(attributes, new TypeToken<ArrayList<SkillCard.Category>>(){}.getType());
    }
}
