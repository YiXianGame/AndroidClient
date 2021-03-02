package com.yixian.make.RPC.Adapt;
import com.yixian.make.Core;
import com.yixian.material.RPC.Annotation.RPCAdapt;
import com.yixian.material.RPC.Annotation.RPCRequest;

public class UserAdapt {
    @RPCAdapt
    public static void SetSkillCardUpdate(Long update)
    {
        Core.liveUser.getValue().setCardRepository_update(update);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
    @RPCAdapt
    public static void SetFriendUpdate(Long update){
        Core.liveUser.getValue().setFriend_update(update);
        Core.repository.userRepository.local_updateFriendUpdate(Core.liveUser.getValue());
    }
    @RPCAdapt
    public static void SetCardRepositoryUpdate(Long timestamp)
    {
        Core.liveUser.getValue().setCardRepository_update(timestamp);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
}
