package com.xianyu.yixian_client.Model.Repository.Interface;

import com.xianyu.yixian_client.Model.Exception.UserException;
import com.xianyu.yixian_client.Model.Room.Entity.Config;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface IRepository{
    //这里是产生的Observable
    Single<Long> registerUser(User user);
    Single<Long> loginUser(User user);
    void insertUser(User user);
    Maybe<User> sync_UserAttribute(User user);
    void updateLocalAccount(User user);
    Maybe<User> queryLocalUser(long id);
    Single<List<User>> queryAllUsers();
    Maybe<User> queryUserByUserName(String userName);

    Maybe<User> queryUserById(long id);
    void insertFriend(Friend... friends);

    void deleteFriend(Friend... friends);

    void updateFriend(Friend... friends);

    Single<List<Friend>> queryFriends(long user_id);

    Single<List<User>> queryAllFriendUsers(long user_id);

    void insertSkillCard(SkillCard... skillCards) ;

    void deleteSkillCard(SkillCard... skillCards);

    void updateSkillCard(SkillCard... skillCards);

    Single<List<SkillCard>> querySkillCardByAuthor(long user_id);

    Maybe<SkillCard> querySkillCardById(long id);

    Single<List<SkillCard>> queryAllSkillCards();

    void insertConfig(Config... configs);

    void updateConfig(Config... configs);

    void deleteConfig(Config... configs);

    Single<List<Config>> queryConfig(int start, int end);
}
