package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.ArrayList;

public interface UserRequest {
    Long RegisterUser(String username,String nickname,String password);
    Long LoginUser(Long id,String username, String md5_password);
    Long Update_CardGroups(User user);
    User Sync_Attribute(Long date);
    @RPCMethod(parameters = "users")
    ArrayList<User> Sync_Attribute(ArrayList<User> dates);
    @RPCMethod(parameters = "users")
    ArrayList<User> Sync_CardGroups(ArrayList<User> dates);
    ArrayList<Friend> Sync_Friend(Long date);
    ArrayList<CardItem> Sync_CardRepository(Long id, Long date);
    Boolean InviteSquad(Long id);
    ArrayList<User> EnterSquad(Long id,String secretKey);
    String CreateSquad(String roomType);
    User Query_UserAttributeById(Long id);
}
