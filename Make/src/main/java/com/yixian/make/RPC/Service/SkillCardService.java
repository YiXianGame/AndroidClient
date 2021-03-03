package com.yixian.make.RPC.Service;

import com.yixian.make.Core;
import com.yixian.material.RPC.Annotation.RPCService;

public class SkillCardService {
    @RPCService
    public static void SetSkillCardUpdate(Long timestamp)
    {
        Core.liveConfig.getValue().setSkillCardUpdate(timestamp);
        Core.repository.configRepository.update(Core.liveConfig.getValue());
    }
}
