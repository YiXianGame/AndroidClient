package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.List;

public interface UserRequest {
    long RegisterUser(String username,String nickname,String password);
    long LoginUser(Long id,String username, String md5_password);
    User Sync_UserAttribute(Long date);
    User QueryUserByUserName(String userName);
    User Query_UserAttributeById(Long id);
    List<User> QueryAllFriendUsers(Long user_id);
}
