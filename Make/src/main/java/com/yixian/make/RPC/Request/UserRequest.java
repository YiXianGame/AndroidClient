package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Annotation.RPCRequest;

import java.util.ArrayList;

public interface UserRequest {
    @RPCRequest
    Long RegisterUser(String username,String nickname,String password);
    @RPCRequest
    Long LoginUser(Long id,String username, String md5_password);
    @RPCRequest
    Long Update_CardGroups(User user);
    @RPCRequest
    User Sync_Attribute(Long date);
    @RPCRequest(parameters = {"List<User>"})
    ArrayList<User> Sync_Attribute(ArrayList<User> dates);
    @RPCRequest(parameters = {"List<User>"})
    ArrayList<User> Sync_CardGroups(ArrayList<User> dates);
    @RPCRequest
    ArrayList<Friend> Sync_Friend(Long date);
    @RPCRequest
    ArrayList<CardItem> Sync_CardRepository(Long id, Long date);
    @RPCRequest
    User Query_UserAttributeById(Long id);
    @RPCRequest
    String CreateSquad(String roomType);
}
