package com.xianyu.yixian_client.Model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface HistoryDao {
    @Insert
    public void insert(History... histories);

    @Update
    public void update(History... skillCards);

    @Delete
    public void delete(History... skillCards);

    @Query("SELECT * FROM history WHERE user_id == :user_id")
    public Single<List<History>> query(long user_id);
}
