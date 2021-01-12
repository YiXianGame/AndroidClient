package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.xianyu.yixian_client.Model.Enums;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Entity
 * @ClassName: Buff
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 21:23
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 21:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "buff")
public class Buff {
    public enum Category { Freeze };
    @PrimaryKey
    long id;//状态ID
    int name;//状态名称
    int duration_Immediate;//效果持续时长
    int duration_Round;//效果持续回合
    int power;//能力
    @Ignore
    Category category;//类型

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDuration_Immediate() {
        return duration_Immediate;
    }

    public void setDuration_Immediate(int duration_Immediate) {
        this.duration_Immediate = duration_Immediate;
    }

    public int getDuration_Round() {
        return duration_Round;
    }

    public void setDuration_Round(int duration_Round) {
        this.duration_Round = duration_Round;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Buff(){

    }
}
