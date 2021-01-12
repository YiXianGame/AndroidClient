package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Entity
 * @ClassName: repository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 21:50
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 21:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "repository",
        primaryKeys = {"user_id","skillcard_id"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id",
                        childColumns = "user_id", onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = SkillCard.class, parentColumns = "id",
                        childColumns = "skillcard_id", onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)}
        )
public class Repository {
    long user_id;
    long skillcard_id;
    String solution;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getSkillcard_id() {
        return skillcard_id;
    }

    public void setSkillcard_id(long skillcard_id) {
        this.skillcard_id = skillcard_id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Repository(){

    }
}
