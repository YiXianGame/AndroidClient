package com.yixian.material.Entity;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.yixian.material.Entity
 * @ClassName: Player
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/4 20:08
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/4 20:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Player {
    private long id;
    private String username = "";//玩家ID也是QQ号（-1时为机器人）
    private String nickname;//玩家昵称
    private int hp;//血量
    private int mp;//能量
    private int hp_max;//血量上限
    private int mp_max;//仙气上限
    private String title = "炼气";//称号
    private int lv = 1;//等级
    private byte[] headImage;
    private Room room;//房间
    private Team team;//队友
    private CardGroup cardGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getHp_max() {
        return hp_max;
    }

    public void setHp_max(int hp_max) {
        this.hp_max = hp_max;
    }

    public int getMp_max() {
        return mp_max;
    }

    public void setMp_max(int mp_max) {
        this.mp_max = mp_max;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public byte[] getHeadImage() {
        return headImage;
    }

    public void setHeadImage(byte[] headImage) {
        this.headImage = headImage;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }
}
