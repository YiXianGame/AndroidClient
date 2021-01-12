package com.xianyu.yixian_client.Model.Room.Entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<SkillCard> getRepository() {
        return repository;
    }

    public void setRepository(ArrayList<SkillCard> repository) {
        this.repository = repository;
    }

    public enum State { Leisure, Ready, Queue, Gaming };
    @PrimaryKey
    private long id;
    private String userName;
    private String nickName;
    private int upgrade_num=0;
    private int create_num=0;
    private int money = 0;
    private String passwords;
    private String information;
    private int battle_Count;//战斗场次
    private int exp;//经验
    private int balances;//金钱
    private int lv = 1;//等级
    private String title = "炼气";//称号
    @Ignore
    private State active = State.Leisure;//玩家当前游戏状态
    private int kills;//击杀数
    private int deaths;//死亡数
    private long registration_date;//注册时间
    @Ignore
    ArrayList<CardGroup> battle_Repository;
    @Ignore
    ArrayList<SkillCard> repository;
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
                ", balances=" + balances +
                ", lv=" + lv +
                ", title='" + title + '\'' +
                ", active=" + active +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", registration_date=" + registration_date +
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

    public int getBalances() {
        return balances;
    }

    public void setBalances(int balances) {
        this.balances = balances;
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

    public long getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(long registration_date) {
        this.registration_date = registration_date;
    }

    public List<CardGroup> getBattle_Repository() {
        return battle_Repository;
    }

    public void setBattle_Repository(ArrayList<CardGroup> battle_Repository) {
        this.battle_Repository = battle_Repository;
    }
}
