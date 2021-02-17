package com.yixian.material.Room.Convert;

import androidx.room.TypeConverter;

import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.SkillCard;

public class FriendConvert {
    @TypeConverter
    public String categoryToString(Friend.Category category){
        return category.toString();
    }
    @TypeConverter
    public Friend.Category stringToCategory(String category){
        return Enum.valueOf(Friend.Category.class,category);
    }
}
