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

public class Item {
    @Expose
    long ownerId;
    @Expose
    long itemId;
    @Expose
    String solution;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
