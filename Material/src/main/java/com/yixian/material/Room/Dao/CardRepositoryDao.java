package com.yixian.material.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Config;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CardRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CardItem... cardItems);

    @Update
    void update(CardItem... cardItems);

    @Delete
    void delete(CardItem... cardItems);
    @Query("DELETE FROM card_repository")
    int deleteAllSync();
    @Query("SELECT * FROM card_repository WHERE ownerId = :id")
    List<CardItem> queryByIdSync(long id);

}
