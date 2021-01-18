package com.xianyu.yixian_client.Model.Repository;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;
import io.reactivex.Single;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.RemoteRepository
 * @ClassName: LocalRepository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/19 23:01
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/19 23:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocalRepository implements ILocalRepository{
    private DataBase_Room db;

    public LocalRepository(DataBase_Room db){
        this.db = db;
    }

    public void insertUser(User user) {
        db.userDao().insert(user);
    }


    @Override
    public void deleteUser(User user) {
        db.userDao().delete(user);
    }

    @Override
    public void updateUser(User user) {
        db.userDao().update(user);
    }

    @Override
    public Single<User> queryUserByUserName(String userName) {
        return db.userDao().queryByUserName(userName);
    }

    @Override
    public Single<User> queryUserById(long id) {
        return db.userDao().queryById(id);
    }

    @Override
    public Single<List<User>> queryAllUsers() {
        return db.userDao().queryAll();
    }


    @Override
    public void insertFriend(Friend... friends) {
        db.friendDao().insert(friends);
    }

    @Override
    public void deleteFriend(Friend... friends) {
        db.friendDao().delete(friends);
    }
    @Override
    public void updateFriend(Friend... friends) {
        db.friendDao().update(friends);
    }
    @Override
    public Single<List<Friend>> queryFriend(long user_id) {
        return db.friendDao().query(user_id);
    }

    @Override
    public void insertSkillCard(SkillCard... skillCards) {
        db.skillCardDao().insert(skillCards);
    }

    @Override
    public void deleteSkillCard(SkillCard... skillCards) {
        db.skillCardDao().delete(skillCards);
    }

    @Override
    public void updateSkillCard(SkillCard... skillCards) {
        db.skillCardDao().update(skillCards);
    }

    @Override
    public Single<List<SkillCard>> querySkillCardByAuthor(long user_id) {
        return db.skillCardDao().queryByAuthorId(user_id);
    }

    @Override
    public Single<SkillCard> querySkillCardById(long id) {
        return db.skillCardDao().queryById(id);
    }

    @Override
    public Single<List<SkillCard>> queryAllSkillCards() {
        return db.skillCardDao().queryAllSkillCards();
    }

}
