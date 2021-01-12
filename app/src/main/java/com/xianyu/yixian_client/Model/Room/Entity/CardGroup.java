package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Entity
 * @ClassName: CardGroup
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 22:31
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 22:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "cardgroup",
            foreignKeys = {
                @ForeignKey(entity = User.class,parentColumns = {"id"},childColumns = {"owner_id"},onDelete = ForeignKey.CASCADE)
            }
        )
public class CardGroup {
    @PrimaryKey
    long id;
    long owner_id;
    String name;
    @Ignore
    List<SkillCard> repository;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillCard> getRepository() {
        return repository;
    }

    public void setRepository(List<SkillCard> repository) {
        this.repository = repository;
    }
    public CardGroup(){

    }
}
