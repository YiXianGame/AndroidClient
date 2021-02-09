package com.yixian.material.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yixian.material.Entity.Config;

import java.util.List;

import io.reactivex.Single;
@Dao
public interface ConfigDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Config... configs);

    @Update
    void update(Config... configs);

    @Delete
    void delete(Config... configs);

    @Query("SELECT * FROM config LIMIT :start,:end")
    Single<List<Config>> query(int start, int end);

}
