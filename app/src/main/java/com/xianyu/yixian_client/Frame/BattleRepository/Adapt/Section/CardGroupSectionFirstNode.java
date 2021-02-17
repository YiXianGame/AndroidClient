package com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section;

import android.util.Pair;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.xianyu.yixian_client.Frame.BattleRepository.BattleRepository_ViewModel;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardGroupSectionFirstNode extends BaseExpandNode {
    CardGroup cardGroup;
    List<BaseNode> children = new ArrayList<>();
    public CardGroupSectionFirstNode(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
        for(Long item : cardGroup.getCards()){
            children.add(new CardGroupSectionSecondNode(Core.liveSkillcards.getValue().get(item)));
        }
        setExpanded(false);
    }

    public List<BaseNode> getChildren() {
        return children;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return children;
    }
}
