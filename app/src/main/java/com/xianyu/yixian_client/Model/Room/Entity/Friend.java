package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;

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
@Entity(tableName = "friends",
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
}