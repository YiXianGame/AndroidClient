package com.yixian.make.RPC.Adapt;

import com.yixian.make.Core;

public class SkillCardAdapt {
    public static void SyncSkillCardUpdate(Long timestamp)
    {
        Core.liveConfig.getValue().setSkillCardUpdate(timestamp);
        Core.repository.configRepository.update(Core.liveConfig.getValue());
    }
}
