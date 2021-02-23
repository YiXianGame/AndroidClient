package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;
import com.yixian.make.Event.ReadyEvent.Args.InviteUserArgs;
import com.yixian.make.Event.ReadyEvent.Args.MatchSuccessArgs;
import com.yixian.material.Entity.User;

import java.util.ArrayList;

public class UserAdapt {
    public static void SetSkillCardUpdate(Long update)
    {
        Core.liveUser.getValue().setSkillCard_update(update);
        Core.repository.userRepository.local_updateSKillCardUpdate(Core.liveUser.getValue());
    }
    public static void SetFriendUpdate(Long update){
        Core.liveUser.getValue().setFriend_update(update);
        Core.repository.userRepository.local_updateFriendUpdate(Core.liveUser.getValue());
    }
    public static void MatchSuccess(ArrayList<User> group_1, ArrayList<User> group_2, int idx, String hostname, String port, String secretKey){
        Core.readyEvent.OnEvent("UserAdapt",new MatchSuccessArgs(group_1,group_2,idx,hostname,port,secretKey));
    }
    public void RefreshSquad(User inviter,String secretKey){
        Core.readyEvent.OnEvent("UserAdapt",new InviteUserArgs(inviter,secretKey));
    }
}
