package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.RPC.Annotation.RPCRequest;

import java.util.ArrayList;

public interface SkillCardRequest {
    @RPCRequest()
    ArrayList<SkillCard> Sync(Long timestamp);
    @RPCRequest(parameters = "longs")
    ArrayList<SkillCard> Query(ArrayList<Long> skillCardIds);
}
