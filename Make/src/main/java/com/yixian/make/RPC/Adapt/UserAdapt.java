package com.yixian.make.RPC.Adapt;

import android.util.Log;

import com.yixian.make.Core;
import com.yixian.material.Log.Log.Tag;

public class UserAdapt {
    public static void SyncSkillCardUpdate(Long update)
    {
        Core.liveUser.getValue().setSkillCard_update(update);
        Core.repository.userRepository.local_updateSKillCardUpdate(Core.liveUser.getValue());
    }
}
