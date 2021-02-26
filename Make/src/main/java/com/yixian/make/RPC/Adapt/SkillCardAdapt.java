package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;
import com.yixian.material.RPC.Annotation.RPCMethod;

public class SkillCardAdapt {
    @RPCMethod()
    public static void SetSkillCardUpdate(Long timestamp)
    {
        Core.liveConfig.getValue().setSkillCardUpdate(timestamp);
        Core.repository.configRepository.update(Core.liveConfig.getValue());
    }
}
