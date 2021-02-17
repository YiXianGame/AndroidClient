package com.yixian.material.Entity;

import android.util.Pair;

import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Entity
 * @ClassName: CardGroup
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 22:31
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 22:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class CardGroup {
    @Expose
    String name;
    @Expose
    ArrayList<Long> cards = new ArrayList<>();

    public ArrayList<Long> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Long> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardGroup(){

    }

}
