package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.Friend;
import com.yixian.material.RPC.Annotation.RPCRequest;

import java.util.List;

public interface FriendRequest {
    @RPCRequest()
    public void insertFriend(Friend... friends);
    @RPCRequest()
    public void deleteFriend(Friend... friends);
    @RPCRequest()
    public void updateFriend(Friend... friends);
    @RPCRequest()
    public List<Friend> queryFriends(Long user_id);
}
