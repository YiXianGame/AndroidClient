package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;

public class SkillCardAdapt {
    public static void SetSkillCardUpdate(Long timestamp)
    {
        Core.liveConfig.getValue().setSkillCardUpdate(timestamp);
        Core.repository.configRepository.update(Core.liveConfig.getValue());
    }
}
