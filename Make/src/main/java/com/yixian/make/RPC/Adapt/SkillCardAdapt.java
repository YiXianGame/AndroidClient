package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;
import com.yixian.material.RPC.Annotation.RPCAdapt;
import com.yixian.material.RPC.Annotation.RPCRequest;

public class SkillCardAdapt {
    @RPCAdapt
    public static void SetSkillCardUpdate(Long timestamp)
    {
        Core.liveConfig.getValue().setSkillCardUpdate(timestamp);
        Core.repository.configRepository.update(Core.liveConfig.getValue());
    }
}
