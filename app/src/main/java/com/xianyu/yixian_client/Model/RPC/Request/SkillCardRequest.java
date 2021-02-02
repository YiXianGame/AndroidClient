package com.xianyu.yixian_client.Model.RPC.Request;

import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;

import java.util.List;

public interface SkillCardRequest {
    public void insertSkillCard(SkillCard... skillCards);
    public void deleteSkillCard(SkillCard... skillCards);
    public void updateSkillCard(SkillCard... skillCards);
    public List<SkillCard> querySkillCardByAuthor(long user_id);
    public SkillCard querySkillCardById(long id);
}
