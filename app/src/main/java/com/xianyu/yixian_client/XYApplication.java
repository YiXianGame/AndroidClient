package com.xianyu.yixian_client;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Pair;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

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
    @GlideModule
    public final class MyGlideModule extends AppGlideModule {}
    @Override
    public void onCreate() {
        super.onCreate();
//        try {
//            init_data();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    private void init_data() throws InterruptedException, ExecutionException {
        Random random = new Random();
        CardGroup cardGroup;
        User owner = new User();
        owner.setNickname("涯丶");
        owner.setMoney(1234);
        owner.setExp(12);
        Observable.create((ObservableOnSubscribe<byte[]>) emitter -> {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Glide.with(this).asBitmap().load(R.drawable.touxiang).submit().get().compress(Bitmap.CompressFormat.JPEG,50,out);
            //owner.setHeadImage(out.toByteArray());
        }).subscribeOn(Schedulers.io()).subscribe();
        owner.setBattleCount(42);
        owner.setLv(2);
        owner.setUsername("839336369");
        owner.setActive(User.State.Leisure);
        owner.setId(123456);
        Core.liveUser.setValue(owner);
        for(int i = 0;i<5;i++){
            History history = new History(random.nextInt(20),true);
            history.setTime(System.currentTimeMillis());
            owner.getHistory().add(history);
        }
        for(int i = 0;i<5;i++){
            History history = new History(random.nextInt(20),false);
            history.setTime(System.currentTimeMillis());
            owner.getHistory().add(history);
        }
        SkillCard skillCard;
        Buff buff;
        for(int i=0;i<2000;i++){
            skillCard = new SkillCard();
            skillCard.setName(getRandomChineseString(10));
            if(random.nextInt(10) > 5) skillCard.getAttributes().put(Attribute.Category.Attack,new Attribute(Attribute.Category.Attack));
            if(random.nextInt(10) > 5) skillCard.getAttributes().put(Attribute.Category.Cure,new Attribute(Attribute.Category.Cure));
            if(random.nextInt(10) > 5) skillCard.getAttributes().put(Attribute.Category.Magic,new Attribute(Attribute.Category.Magic));
            if(random.nextInt(10) > 5) skillCard.getAttributes().put(Attribute.Category.Physics,new Attribute(Attribute.Category.Physics));
            if(random.nextInt(10) > 5) skillCard.getAttributes().put(Attribute.Category.Eternal,new Attribute(Attribute.Category.Eternal));
            skillCard.setAuthorId(owner.getId());
            skillCard.setId(i);
            skillCard.setMp(random.nextInt(20));
            skillCard.setAuxiliaryHp(random.nextInt(20));
            skillCard.setAuxiliaryMp(random.nextInt(20));
            skillCard.setEnemyHp(random.nextInt(20));
            skillCard.setEnemyMp(random.nextInt(20));
            skillCard.setProbability(random.nextInt(100));
            skillCard.setDescription("卡牌描述");
            for(int j=0;j<random.nextInt(5);j++){
                buff = new Buff();
                buff.setCategory(Buff.Category.Freeze);
                skillCard.getAuxiliary_buffs().put(Buff.Category.Freeze,buff);
            }
            for(int j=0;j<random.nextInt(5);j++){
                buff = new Buff();
                buff.setCategory(Buff.Category.Freeze);
                skillCard.getEnemy_buffs().put(Buff.Category.Freeze,buff);
            }
            repository.insertSkillCard(skillCard);
        }
        for(int i = 0;i<5;i++){
            cardGroup = new CardGroup();
            cardGroup.setName(getRandomChineseString(2));
            for(int j=0;j<random.nextInt(10)+10;j++){
                cardGroup.getCards().add(new Pair<>((long)random.nextInt(30),getRandomChineseString(3)));
            }
            owner.getCardGroups().add(cardGroup);
        }
        //repository.insertUser(owner);

        Thread.sleep(1000);
        ArrayList<Friend> friends = new ArrayList<>();
        for(int i=1;i<50;i++){
            User user = new User();
            user.setId(i);
            user.setNickname("用户" + random.nextInt());
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattleCount(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUsername("" + random.nextInt());
            user.setActive(User.State.Leisure);
            Friend friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            //repository.insertUser(user);
            friends.add(friend);
        }
        for(int i=50;i<100;i++){
            User user = new User();
            user.setId(i);
            user.setNickname("用户" + random.nextInt());
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattleCount(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUsername("" + random.nextInt());
            user.setActive(User.State.Gaming);
            Friend friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            //repository.insertUser(user);
            friends.add(friend);
        }
        for(int i=100;i<150;i++){
            User user = new User();
            user.setId(i);
            user.setNickname("用户" + random.nextInt(30));
            user.setMoney(random.nextInt(1000));
            user.setExp(random.nextInt(200));
            user.setBattleCount(random.nextInt(100));
            user.setLv(random.nextInt(10));
            user.setUsername("" + random.nextInt());
            user.setActive(User.State.Offline);
            Friend friend = new Friend();
            friend.setUser_1(owner.getId());
            friend.setUser_2(user.getId());
            //repository.insertUser(user);
            friends.add(friend);
        }
        Thread.sleep(1000);
        for(int i=0;i<friends.size();i++){
            repository.insertFriend(friends.get(i));
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
