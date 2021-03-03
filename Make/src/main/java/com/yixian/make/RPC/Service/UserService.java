package com.yixian.make.RPC.Service;
import com.yixian.make.Core;
import com.yixian.material.RPC.Annotation.RPCService;

public class UserService {
    @RPCService
    public static void SetSkillCardUpdate(Long update)
    {
        Core.liveUser.getValue().setCardRepository_update(update);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
    @RPCService
    public static void SetFriendUpdate(Long update){
        Core.liveUser.getValue().setFriend_update(update);
        Core.repository.userRepository.local_updateFriendUpdate(Core.liveUser.getValue());
    }
    @RPCService
    public static void SetCardRepositoryUpdate(Long timestamp)
    {
        Core.liveUser.getValue().setCardRepository_update(timestamp);
        Core.repository.userRepository.local_updateCardRepositoryUpdate(Core.liveUser.getValue());
    }
}
