package com.yixian.material.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.yixian.material.Room.Convert.ActiveConvert;
import com.yixian.material.Room.Convert.FriendConvert;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Entity
 * @ClassName: Friend
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 21:48
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 21:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "friend",
        primaryKeys = {"user_1","user_2"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id",
                        childColumns = "user_1", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "id",
                        childColumns = "user_2", onDelete = ForeignKey.CASCADE)},
        indices = {
        @Index(value = {"user_1"}),
        @Index(value = {"user_2"})
        }

)
public class Friend {
    public enum Category { Friend }
    @Expose
    long user_1;
    @Expose
    long user_2;
    @Expose
    @TypeConverters(FriendConvert.class)
    private Category category = Category.Friend;//玩家当前游戏状态

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getUser_1() {
        return user_1;
    }

    public void setUser_1(long user_1) {
        this.user_1 = user_1;
    }

    public long getUser_2() {
        return user_2;
    }

    public void setUser_2(long user_2) {
        this.user_2 = user_2;
    }

    public Friend(){

    }
}
