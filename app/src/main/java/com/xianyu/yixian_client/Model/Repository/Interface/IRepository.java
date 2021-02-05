package com.xianyu.yixian_client.Model.Repository.Interface;

import com.xianyu.yixian_client.Model.Room.Entity.Config;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;

 public interface IRepository {
    //这里是产生的Observable
     Single<Long> registerUser(User user);
     Single<Long> loginUser(User user);
     void insertUser(User user);
     void updateUserPassword(User user);
     Single<User> update_UserAttribute(User user);
     Single<List<User>> queryAllUsers();
     Single<User> queryUserByUserName(String userName);

     Single<User> queryUserById(long id);
     void insertFriend(Friend... friends);

     void deleteFriend(Friend... friends);

     void updateFriend(Friend... friends);

     Single<List<Friend>> queryFriends(long user_id);

     Single<List<User>> queryAllFriendUsers(long user_id);

     void insertSkillCard(SkillCard... skillCards) ;

     void deleteSkillCard(SkillCard... skillCards);

     void updateSkillCard(SkillCard... skillCards);

     Single<List<SkillCard>> querySkillCardByAuthor(long user_id);

     Single<SkillCard> querySkillCardById(long id);

     Single<List<SkillCard>> queryAllSkillCards();

     void insertConfig(Config... configs);

     void updateConfig(Config... configs);

     void deleteConfig(Config... configs);

     Single<Config> queryConfig(int start, int end);
}
