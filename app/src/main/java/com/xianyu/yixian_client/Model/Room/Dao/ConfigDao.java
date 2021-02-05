package com.xianyu.yixian_client.Model.Room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.xianyu.yixian_client.Model.Room.Entity.Config;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;

import io.reactivex.Single;

public interface ConfigDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Config... configs);

    @Update
    void update(Config... configs);

    @Delete
    void delete(Config... configs);

    @Query("SELECT * FROM config LIMIT :start,:end")
    Single<Config> query(int start, int end);

}
