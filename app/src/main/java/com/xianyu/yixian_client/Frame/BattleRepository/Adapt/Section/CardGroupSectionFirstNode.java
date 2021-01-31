package com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section;

import android.util.Log;
import android.util.Pair;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardGroupSectionFirstNode extends BaseExpandNode {
    CardGroup cardGroup;
    List<BaseNode> children = new ArrayList<>();
    public CardGroupSectionFirstNode(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
        for(Pair<Long, String> item : cardGroup.getCards()){
            children.add(new CardGroupSectionSecondNode(item));
        }
        setExpanded(false);
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
