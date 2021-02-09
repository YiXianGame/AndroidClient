package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.yixian.material.Entity.SkillCard;

public class CategoryConvert {
    @TypeConverter
    public String categoryToString(SkillCard.Category category){
        return category.toString();
    }
    @TypeConverter
    public SkillCard.Category stringToCategory(String category){
        return Enum.valueOf(SkillCard.Category.class,category);
    }
}
