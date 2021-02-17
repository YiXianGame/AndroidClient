package com.yixian.material.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.yixian.material.Room.Convert.ActiveConvert;
import com.yixian.material.Room.Convert.BytesConvert;
import com.yixian.material.Room.Convert.GroupConvert;
import com.yixian.material.Room.Convert.HistoryConvert;

import java.util.ArrayList;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/16 15:05
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/16 15:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "user")
public class User
{
    public enum State { Leisure, Ready, Queue, Gaming , Offline};
    @Expose
    @PrimaryKey
    private long id;
    @Expose
    private String username="";
    @TypeConverters(BytesConvert.class)
    @Expose
    private byte[] headImage;
    @Expose
    private String nickname="";
    @Expose
    private String password="";
    @Expose
    private int upgrade_num=0;
    @Expose
    private int create_num=0;
    @Expose
    private long money = 0;
    @Expose
    private String personalSignature="";
    @Expose
    private int battleCount;//战斗场次
    @Expose
    private long exp;//经验
    @Expose
    private int lv = 1;//等级
    @Expose
    private String title = "炼气";//称号
    @Expose
    @TypeConverters(ActiveConvert.class)
    private State active = State.Offline;//玩家当前游戏状态
    @Expose
    private int kills;//击杀数
    @Expose
    private int deaths;//死亡数
    @Expose
    long registerDate;//注册日期
    @Expose
    long attribute_update;//个人信息更新日期
    @Expose
    long skillCard_update;//卡牌更新日期
    @Expose
    long headImage_update;//头像更新日期
    @Expose
    long cardGroup_update;//卡组更新日期
    @Expose
    long friend_update;//好友更新日期
    @Expose
    @TypeConverters(GroupConvert.class)
    ArrayList<CardGroup> cardGroups = new ArrayList<>();
    @Expose
    @TypeConverters(HistoryConvert.class)
    ArrayList<History> history = new ArrayList<>();

    public long getFriend_update() {
        return friend_update;
    }

    public void setFriend_update(long friend_update) {
        this.friend_update = friend_update;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCardGroup_update() {
        return cardGroup_update;
    }

    public void setCardGroup_update(long cardGroup_update) {
        this.cardGroup_update = cardGroup_update;
    }

    public long getSkillCard_update() {
        return skillCard_update;
    }

    public void setSkillCard_update(long skillCard_update) {
        this.skillCard_update = skillCard_update;
    }

    public long getHeadImage_update() {
        return headImage_update;
    }

    public void setHeadImage_update(long headImage_update) {
        this.headImage_update = headImage_update;
    }

    public byte[] getHeadImage() {
        return headImage;
    }

    public void setHeadImage(byte[] headImage) {
        this.headImage = headImage;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public long getAttribute_update() {
        return attribute_update;
    }

    public void setAttribute_update(long update_data) {
        this.attribute_update = update_data;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", upgrade_num=" + upgrade_num +
                ", create_num=" + create_num +
                ", money=" + money +
                ", personalSignature='" + personalSignature + '\'' +
                ", battleCount=" + battleCount +
                ", exp=" + exp +
                ", lv=" + lv +
                ", title='" + title + '\'' +
                ", active=" + active +
                ", kills=" + kills +
                ", deaths=" + deaths +
                '}';
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUpgrade_num() {
        return upgrade_num;
    }

    public void setUpgrade_num(int upgrade_num) {
        this.upgrade_num = upgrade_num;
    }

    public int getCreate_num() {
        return create_num;
    }

    public void setCreate_num(int create_num) {
        this.create_num = create_num;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getPersonalSignature() {
        return personalSignature;
    }

    public void setPersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;
    }

    public int getBattleCount() {
        return battleCount;
    }

    public void setBattleCount(int battleCount) {
        this.battleCount = battleCount;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }


    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public State getActive() {
        return active;
    }

    public void setActive(State active) {
        this.active = active;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<CardGroup> getCardGroups() {
        return cardGroups;
    }

    public void setCardGroups(ArrayList<CardGroup> cardGroups) {
        this.cardGroups = cardGroups;
    }
}
