package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;
import java.util.List;

public interface SkillCardRequest {
    ArrayList<SkillCard> Sync(Long timestamp);
    ArrayList<SkillCard> SyncUser(Long timestamp);
}
