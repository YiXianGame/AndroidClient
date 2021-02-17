package com.yixian.material.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.User;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FriendDao {
    @Insert
    public void insert(Friend... friends);

    @Update
    public void update(Friend... friends);

    @Delete
    public void delete(Friend... friends);

    @Query("SELECT * FROM friend WHERE user_1 = :user_id OR user_2 = :user_id")
    public Single<List<Friend>> query(long user_id);

    @Query("SELECT * FROM friend WHERE user_1 = :user_id OR user_2 = :user_id")
    public List<Friend> query_Sync(long user_id);

    @Query("SELECT * FROM user " +
            "INNER JOIN friend ON (user_1 = :user_id AND user_2 = user.id) OR (user_1 = user.id AND user_2 = :user_id)")
    public Single<List<User>> queryWithUsers(long user_id);
}
