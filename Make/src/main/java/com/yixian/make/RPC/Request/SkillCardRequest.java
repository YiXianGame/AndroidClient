package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.ArrayList;

public interface SkillCardRequest {
    @RPCMethod()
    ArrayList<SkillCard> Sync(Long timestamp);
    @RPCMethod(parameters = "longs")
    ArrayList<SkillCard> Query(ArrayList<Long> skillCardIds);
}
