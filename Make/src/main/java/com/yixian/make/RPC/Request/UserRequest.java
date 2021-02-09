package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.User;

import java.util.List;

public interface UserRequest {
    long RegisterUser(String username,String nickname,String password);
    long LoginUser(long id,String username, String md5_password);
    User Sync_UserAttribute(long date);
    User QueryUserByUserName(String userName);
    User Query_UserAttributeById(long id);
    List<User> QueryAllFriendUsers(long user_id);
}
