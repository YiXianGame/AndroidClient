package com.yixian.material.Room.Relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.List;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Relation
 * @ClassName: UserWithSkillCard
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 23:42
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 23:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserWithSkillCard {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "ownerId",
            entityColumn = "skillcard_id",
            associateBy = @Junction(CardItem.class)
    )
    public List<SkillCard> skillCards;
    public UserWithSkillCard(){

    }
}
