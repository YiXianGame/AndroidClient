package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

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

public class CardGroup {
    @Expose
    String name;
    @Expose
    @TypeConverters(ArrayList.class)
    ArrayList<Long> cards_id = new ArrayList<>();
    @Ignore
    ArrayList<SkillCard> cards  = new ArrayList<>();

    public ArrayList<Long> getCards_id() {
        return cards_id;
    }

    public void setCards_id(ArrayList<Long> cards_id) {
        this.cards_id = cards_id;
    }

    public ArrayList<SkillCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<SkillCard> cards) {
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
