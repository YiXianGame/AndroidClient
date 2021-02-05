package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class Config {
    @PrimaryKey
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
