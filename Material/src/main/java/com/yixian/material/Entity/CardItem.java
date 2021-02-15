package com.yixian.material.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.google.gson.annotations.Expose;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Entity
 * @ClassName: repository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 21:50
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 21:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "card_repository",
        primaryKeys = {"ownerId","itemId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id",
                        childColumns = "ownerId", onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = SkillCard.class, parentColumns = "id",
                        childColumns = "itemId", onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)},
        indices = {
                @Index(value = {"ownerId"}),
                @Index(value = {"itemId"})}
        )
public class CardItem extends Item{

}
