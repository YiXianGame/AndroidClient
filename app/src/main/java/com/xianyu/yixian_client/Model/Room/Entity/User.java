package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.xianyu.yixian_client.Model.Room.Convert.ActiveConvert;
import com.xianyu.yixian_client.Model.Room.Convert.GroupConvert;
import com.xianyu.yixian_client.Model.Room.Convert.HistoryConvert;

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
    private String userName;
    @Expose
    private String nickName;
    @Expose
    private int upgrade_num=0;
    @Expose
    private int create_num=0;
    @Expose
    private int money = 0;
    @Expose
    private String passwords;
    @Expose
    private String information;
    @Expose
    private int battle_Count;//战斗场次
    @Expose
    private int exp;//经验
    @Expose
    private int lv = 1;//等级
    @Expose
    private String title = "炼气";//称号
    @Expose
    @TypeConverters(ActiveConvert.class)
    private Enum<State> active = State.Offline;//玩家当前游戏状态
    @Expose
    private int kills;//击杀数
    @Expose
    private int deaths;//死亡数
    @Expose
    int registerDate;//卡牌注册日期

    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    @Expose
    int update;//卡牌更新日期


    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update_data) {
        this.update = update_data;
    }
    @Expose
    @TypeConverters(GroupConvert.class)
    ArrayList<CardGroup> cardGroups = new ArrayList<>();
    @Expose
    @TypeConverters(HistoryConvert.class)
    ArrayList<History> history = new ArrayList<>();

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    public User(){

    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", upgrade_num=" + upgrade_num +
                ", create_num=" + create_num +
                ", money=" + money +
                ", passwords='" + passwords + '\'' +
                ", information='" + information + '\'' +
                ", battle_Count=" + battle_Count +
                ", exp=" + exp +
                ", lv=" + lv +
                ", title='" + title + '\'' +
                ", active=" + active +
                ", kills=" + kills +
                ", deaths=" + deaths +
                '}';
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getBattle_Count() {
        return battle_Count;
    }

    public void setBattle_Count(int battle_Count) {
        this.battle_Count = battle_Count;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
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

    public Enum<State> getActive() {
        return active;
    }

    public void setActive(Enum<State> active) {
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
