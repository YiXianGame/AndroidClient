package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserRequest {
    Long RegisterUser(String username,String nickname,String password);
    Long LoginUser(Long id,String username, String md5_password);
    Long Update_CardGroups(User user);
    User Sync_UserAttribute(Long date);
    ArrayList<CardGroup> Sync_UserCardGroup(Long date);
    ArrayList<CardItem> Sync_UserSkillCards(Long id,Long date);
    User Query_UserByUserName(String userName);
    User Query_UserAttributeById(Long id);
    List<User> Query_AllFriendUsers(Long user_id);
}
