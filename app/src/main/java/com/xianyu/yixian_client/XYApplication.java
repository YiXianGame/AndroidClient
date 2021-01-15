package com.xianyu.yixian_client;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client
 * @ClassName: XYApplication
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/20 12:32
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/20 12:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@HiltAndroidApp
public class XYApplication extends Application {
    @Inject
    Repository repository;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            init_data();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init_data() throws InterruptedException {
        Random random = new Random();
        User user;
        Friend friend;
        CardGroup cardGroup;
        User owner = new User();
        owner.setNickName("涯丶");
        owner.setMoney(1234);
        owner.setExp(12);
        owner.setBattle_Count(42);
        owner.setLv(2);
        owner.setUserName("839336369");
        owner.setActive(User.State.Leisure);
        owner.setId(123456);
        History history;
        for(int i = 0;i<5;i++){
            history = new History(random.nextInt(20),true);
            owner.getHistory().add(history);
        }
        for(int i = 0;i<5;i++){
            history = new History(random.nextInt(20),false);
            owner.getHistory().add(history);
        }
        SkillCard skillCard;
        Buff buff;
        for(int i=0;i<30;i++){
            skillCard = new SkillCard();
            skillCard.setName(getRandomChineseString(random.nextInt(3)));
            if(random.nextInt(10) > 5) skillCard.setAttack(true);
            if(random.nextInt(10) > 5) skillCard.setEternal(true);
            if(random.nextInt(10) > 5) skillCard.setCure(true);
            if(random.nextInt(10) > 5) skillCard.setPhysics(true);
            if(random.nextInt(10) > 5) skillCard.setMagic(true);
            skillCard.setAuthor_id(owner.getId());
            skillCard.setId(i);
            skillCard.setMp(random.nextInt(20));
            skillCard.setAuxiliary_hp(random.nextInt(20));
            skillCard.setAuxiliary_mp(random.nextInt(20));
            skillCard.setEnemy_hp(random.nextInt(20));
            skillCard.setEnemy_mp(random.nextInt(20));
            skillCard.setProbability(random.nextInt(100));
            skillCard.setDescription(getRandomChineseString(100));
            for(int j=0;j<random.nextInt(5);j++){
                buff = new Buff();
                buff.setName(getRandomChineseString(2));
                buff.setCategory(Buff.Category.Freeze);
                skillCard.getBuffs().add(buff);
            }
            repository.insertSkillCard(skillCard);
        }
        for(int i = 0;i<5;i++){
            cardGroup = new CardGroup();
            cardGroup.setName(getRandomChineseString(2));
            for(int j=0;j<random.nextInt(10)+10;j++){
                cardGroup.getCards_id().add((long)random.nextInt(30));
            }
            owner.getCardGroups().add(cardGroup);
        }
        repository.insertUser(owner);

        for(int i=1;i<5;i++){
            user = new User();
            user.setId(i);
            user.setNickName("用户" + random.nextInt());
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattle_Count(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUserName("" + random.nextInt());
            user.setActive(User.State.Leisure);
            friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            repository.insertUser(user);
            Thread.sleep(200);
            repository.insertFriend(friend);
        }
        for(int i=5;i<10;i++){
            user = new User();
            user.setId(i);
            user.setNickName("用户" + random.nextInt());
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattle_Count(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUserName("" + random.nextInt());
            user.setActive(User.State.Gaming);
            friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            repository.insertUser(user);
            Thread.sleep(200);
            repository.insertFriend(friend);
        }
        for(int i=10;i<15;i++){
            user = new User();
            user.setId(i);
            user.setNickName("用户" + random.nextInt(30));
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattle_Count(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUserName("" + random.nextInt());
            user.setActive(User.State.Offline);
            friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            repository.insertUser(user);
            Thread.sleep(200);
            repository.insertFriend(friend);
        }
    }
    public String getRandomChineseString(int n)  {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<n;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuilder.append (str);
        }
        return stringBuilder.toString();
    }
}
