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
    int duration;//效果持续时长
    @Expose
    int power;//能力
    @Expose
    Category category;//类型
    User owner;//Buff拥有者
    long expire;//到期时间
    boolean isExpire;//到期时间

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public boolean isExpire() {
        return isExpire;
    }

    public void setExpire(boolean expire) {
        isExpire = expire;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
