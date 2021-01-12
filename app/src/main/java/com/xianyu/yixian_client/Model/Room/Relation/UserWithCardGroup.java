package com.xianyu.yixian_client.Model.Room.Relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Relation
 * @ClassName: UserWithCardGroup
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 23:14
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 23:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserWithCardGroup {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "owner_id"
    )
    public List<com.xianyu.yixian_client.Model.Room.Entity.CardGroup> CardGroup;
    public UserWithCardGroup(){

    }
}
