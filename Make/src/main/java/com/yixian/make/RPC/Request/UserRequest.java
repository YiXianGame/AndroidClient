package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.ArrayList;

public interface UserRequest {
    @RPCMethod()
    Long RegisterUser(String username,String nickname,String password);
    @RPCMethod()
    Long LoginUser(Long id,String username, String md5_password);
    @RPCMethod()
    Long Update_CardGroups(User user);
    @RPCMethod()
    User Sync_Attribute(Long date);
    @RPCMethod(parameters = "users")
    ArrayList<User> Sync_Attribute(ArrayList<User> dates);
    @RPCMethod(parameters = "users")
    ArrayList<User> Sync_CardGroups(ArrayList<User> dates);
    @RPCMethod()
    ArrayList<Friend> Sync_Friend(Long date);
    @RPCMethod()
    ArrayList<CardItem> Sync_CardRepository(Long id, Long date);
    @RPCMethod()
    Boolean InviteSquad(Long id);
    @RPCMethod()
    ArrayList<User> EnterSquad(Long id,String secretKey);
    @RPCMethod()
    String CreateSquad(String roomType);
    @RPCMethod()
    void StartMatch();
    @RPCMethod()
    User Query_UserAttributeById(Long id);
}
