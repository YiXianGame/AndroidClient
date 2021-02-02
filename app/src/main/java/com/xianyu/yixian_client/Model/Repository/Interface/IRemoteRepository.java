package com.xianyu.yixian_client.Model.Repository.Interface;

import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;

public interface IRemoteRepository {
    public long registerUser(User user);
    public void updateUser(User user);
    public User queryUserByUserName(String userName);
    public User queryUserById(long id);
    public List<User> queryAllFriendUsers(long user_id);

    public void insertFriend(Friend... friends);
    public void deleteFriend(Friend... friends);
    public void updateFriend(Friend... friends);
    public List<Friend> queryFriends(long user_id);

    public void insertSkillCard(SkillCard... skillCards);
    public void deleteSkillCard(SkillCard... skillCards);
    public void updateSkillCard(SkillCard... skillCards);
    public List<SkillCard> querySkillCardByAuthor(long user_id);
    public SkillCard querySkillCardById(long id);


}
