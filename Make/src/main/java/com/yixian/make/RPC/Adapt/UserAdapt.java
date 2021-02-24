package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;
import com.yixian.make.Event.ReadyEvent.Args.InviteUserArgs;
import com.yixian.make.Event.ReadyEvent.Args.MatchSuccessArgs;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.ArrayList;

public class UserAdapt {
    public static void SetSkillCardUpdate(Long update)
    {
        Core.liveUser.getValue().setCardRepository_update(update);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
    public static void SetFriendUpdate(Long update){
        Core.liveUser.getValue().setFriend_update(update);
        Core.repository.userRepository.local_updateFriendUpdate(Core.liveUser.getValue());
    }
    @RPCMethod(parameters = "users-users-int-string-string-string")
    public static  void MatchSuccess(ArrayList<User> group_1, ArrayList<User> group_2, int idx, String hostname, String port, String secretKey){
        Core.readyEvent.OnEvent("UserAdapt",new MatchSuccessArgs(group_1,group_2,idx,hostname,port,secretKey));
    }
    public void RefreshSquad(User inviter,String secretKey){
        Core.readyEvent.OnEvent("UserAdapt",new InviteUserArgs(inviter,secretKey));
    }
    public static void SetCardRepositoryUpdate(Long timestamp)
    {
        Core.liveUser.getValue().setCardRepository_update(timestamp);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
}
