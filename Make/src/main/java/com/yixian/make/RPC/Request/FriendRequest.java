package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.Friend;

import java.util.List;

public interface FriendRequest {
    public void insertFriend(Friend... friends);
    public void deleteFriend(Friend... friends);
    public void updateFriend(Friend... friends);
    public List<Friend> queryFriends(long user_id);
}
