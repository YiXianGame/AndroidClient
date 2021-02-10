package com.yixian.make.RPC.Request;

import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;
import java.util.List;

public interface SkillCardRequest {
    ArrayList<SkillCard> sync(long timestamp);
    ArrayList<SkillCard> syncUser(long timestamp);
    ArrayList<SkillCard> Test(ArrayList<SkillCard> list);
}
