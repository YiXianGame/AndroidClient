package com.yixian.material.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.yixian.material.Room.Convert.AttributeConvert;
import com.yixian.material.Room.Convert.BuffConvert;
import com.yixian.material.Room.Convert.CategoryConvert;

import java.util.ArrayList;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Entity
 * @ClassName: SkillCard
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/1/8 20:25
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/1/8 20:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "skillcard")
public class SkillCard {
    @TypeConverters(CategoryConvert.class)
    public enum Category {Physics,Magic,Attack,Cure,Eternal };
    @Expose
    @PrimaryKey
    long id;//卡牌ID-
    @Expose
    String name = "";//卡牌名字
    @Expose
    String description = "";//卡牌描述
    @Expose
    int mp;//释放卡牌所需消耗的仙气值
    @Expose
    int probability;//卡牌觉醒的概率
    @Expose
    int auxiliaryHp;//己方HP效果
    @Expose
    int auxiliaryMp;//己方MP效果
    @Expose
    int enemyHp;//敌人HP效果
    @Expose
    int enemyMp;//敌人MP效果
    @Expose
    long authorId;//作者ID
    @Expose
    int maxEnemy;//最大锁定敌人数 魂命
    @Expose
    int maxAuxiliary;//最大锁定友军数 灵命
    @Expose
    int registerDate;//卡牌注册日期
    @Expose
    int attributeUpdate;//卡牌更新日期
    @Expose
    @TypeConverters(BuffConvert.class)
    ArrayList<Buff> enemyBuff = new ArrayList<>();
    @Expose
    @TypeConverters(BuffConvert.class)
    ArrayList<Buff> auxiliaryBuff = new ArrayList<>();
    @Expose
    @TypeConverters(AttributeConvert.class)
    ArrayList<Category> category = new ArrayList<>();
    @Ignore
    ArrayList<User> enemy = new ArrayList<>();
    @Ignore
    ArrayList<User> auxiliary = new ArrayList<>();

    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int register_data) {
        this.registerDate = register_data;
    }

    public int getAttributeUpdate() {
        return attributeUpdate;
    }

    public void setAttributeUpdate(int update_data) {
        this.attributeUpdate = update_data;
    }

    public ArrayList<Buff> getEnemyBuff() {
        return enemyBuff;
    }

    public void setEnemyBuff(ArrayList<Buff> enemyBuff) {
        this.enemyBuff = enemyBuff;
    }

    public ArrayList<Buff> getAuxiliaryBuff() {
        return auxiliaryBuff;
    }

    public void setAuxiliaryBuff(ArrayList<Buff> auxiliaryBuff) {
        this.auxiliaryBuff = auxiliaryBuff;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public int getAuxiliaryHp() {
        return auxiliaryHp;
    }

    public void setAuxiliaryHp(int auxiliaryHp) {
        this.auxiliaryHp = auxiliaryHp;
    }

    public int getAuxiliaryMp() { return auxiliaryMp; }

    public void setAuxiliaryMp(int auxiliaryMp) {
        this.auxiliaryMp = auxiliaryMp;
    }

    public int getEnemyHp() {
        return enemyHp;
    }

    public void setEnemyHp(int enemyHp) {
        this.enemyHp = enemyHp;
    }

    public int getEnemyMp() {
        return enemyMp;
    }

    public void setEnemyMp(int enemyMp) {
        this.enemyMp = enemyMp;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }

    public int getMaxAuxiliary() {
        return maxAuxiliary;
    }

    public void setMaxAuxiliary(int maxAuxiliary) {
        this.maxAuxiliary = maxAuxiliary;
    }
}
