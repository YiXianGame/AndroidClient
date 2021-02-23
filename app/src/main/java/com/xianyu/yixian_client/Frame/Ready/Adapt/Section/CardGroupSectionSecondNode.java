package com.xianyu.yixian_client.Frame.Ready.Adapt.Section;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.yixian.material.Entity.SkillCard;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CardGroupSectionSecondNode extends BaseNode {
    SkillCard skillcard;
    public CardGroupSectionSecondNode(SkillCard skillcards) {
        this.skillcard = skillcards;
    }

    public SkillCard getSkillcard() {
        return skillcard;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
