package com.xianyu.yixian_client.Model.RPC.Request;

import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;

public interface UserRequest
{
    public User test(User user);
    public long RegisterUser(User user);
    public void UpdateUser(User user);
    public User QueryUserByUserName(String userName);
    public User QueryUserById(long id);
    public List<User> QueryAllFriendUsers(long user_id);
}
