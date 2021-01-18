package com.xianyu.yixian_client.Model.Room.Entity;

import com.google.gson.annotations.Expose;

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

public class Buff {
    public enum Category { Freeze };
    @Expose
    String name;//状态名称
    @Expose
    int duration_Immediate;//效果持续时长
    @Expose
    int duration_Round;//效果持续回合
    @Expose
    int power;//能力
    @Expose
    Category category;//类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
