package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.Friend;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.List;

public interface FriendRequest {
    @RPCMethod()
    public void insertFriend(Friend... friends);
    @RPCMethod()
    public void deleteFriend(Friend... friends);
    @RPCMethod()
    public void updateFriend(Friend... friends);
    @RPCMethod()
    public List<Friend> queryFriends(Long user_id);
}
