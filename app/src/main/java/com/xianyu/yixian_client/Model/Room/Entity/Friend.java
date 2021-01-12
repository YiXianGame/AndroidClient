package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Entity
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
                        childColumns = "user_2", onDelete = ForeignKey.CASCADE)}
)
public class Friend {
    long user_1;
    long user_2;
    String solution;

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Friend(){

    }
}
