package com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section;

import android.util.Pair;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CardGroupSectionSecondNode extends BaseNode {
    Pair<Long, String> skillcard;
    public CardGroupSectionSecondNode(Pair<Long, String> skillcards) {
        this.skillcard = skillcards;
    }

    public Pair<Long, String> getSkillcard() {
        return skillcard;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
