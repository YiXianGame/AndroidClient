package com.xianyu.yixian_client.Model.Repository.Interface;

import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.repository
 * @ClassName: ILocalRepository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/24 3:28
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/24 3:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ILocalRepository {

    public void insertUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
    public Single<User> queryUserByUserName(String userName);
    public Single<User> queryUserById(long id);
    public Single<List<User>> queryAllUsers();


    public void insertFriend(Friend... friends);
    public void deleteFriend(Friend... friends);
    public void updateFriend(Friend... friends);
    public Single<List<Friend>> queryFriends(long user_id);

    public void insertSkillCard(SkillCard... skillCards);
    public void deleteSkillCard(SkillCard... skillCards);
    public void updateSkillCard(SkillCard... skillCards);
    public Single<List<SkillCard>> querySkillCardByAuthor(long user_id);
    public Single<SkillCard> querySkillCardById(long id);
    public Single<List<SkillCard>> queryAllSkillCards();
    public Single<List<User>> queryAllFriendUsers(long user_id);
}
