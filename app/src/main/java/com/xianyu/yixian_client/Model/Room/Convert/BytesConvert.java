package com.xianyu.yixian_client.Model.Room.Convert;


import androidx.room.TypeConverter;

public class BytesConvert {
    @TypeConverter
    public String arrayListToString(byte[] bitmap){
        return bitmap.toString();
    }
    @TypeConverter
    public byte[] stringToArrayList(String bitmap){
        return bitmap.getBytes();
    }
}
