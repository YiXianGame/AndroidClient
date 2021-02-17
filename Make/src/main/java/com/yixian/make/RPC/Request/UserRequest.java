package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserRequest {
    Long RegisterUser(String username,String nickname,String password);
    Long LoginUser(Long id,String username, String md5_password);
    Long Update_CardGroups(User user);
    User Sync_Attribute(Long date);
    ArrayList<User> Sync_Attribute(ArrayList<User> dates);
    ArrayList<Friend> Sync_Friend(Long date);
    ArrayList<CardItem> Sync_SkillCards(Long id, Long date);
    User Query_UserAttributeById(Long id);
}
