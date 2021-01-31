package com.xianyu.yixian_client.Frame.BattleRepository.Adapt;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.chad.library.adapter.base.module.DraggableModule;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirst;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionSecond;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionSecondNode;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends BaseNodeAdapter implements DraggableModule {
    private ArrayList<CardGroup> groups;
    public GroupAdapter() {
        super();
        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new CardGroupSectionFirst());
        // 普通的item provider
        addNodeProvider(new CardGroupSectionSecond());
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int i) {
        BaseNode node = data.get(i);
        if(node instanceof CardGroupSectionFirstNode){
            return 0;
        }
        else if(node instanceof CardGroupSectionSecondNode){
            return 1;
        }
        return -1;
    }
    protected class DiffCallBack extends DiffUtil.ItemCallback<BaseNode>{
        @Override
        public boolean areItemsTheSame(@NonNull BaseNode oldItem, @NonNull BaseNode newItem) {
            if(oldItem instanceof CardGroupSectionFirstNode && newItem instanceof CardGroupSectionFirstNode){
                return ((CardGroupSectionFirstNode)(oldItem)).getCardGroup().getName() == ((CardGroupSectionFirstNode)(newItem)).getCardGroup().getName();
            }
            else if(oldItem instanceof CardGroupSectionSecondNode && newItem instanceof CardGroupSectionSecondNode){
                return ((CardGroupSectionSecondNode)(oldItem)).getSkillcard().first == ((CardGroupSectionSecondNode)(newItem)).getSkillcard().first;
            }
            return false;
        }
        @Override
        public boolean areContentsTheSame(@NonNull BaseNode oldItem, @NonNull BaseNode newItem) {
            if(oldItem instanceof CardGroupSectionFirstNode && newItem instanceof CardGroupSectionFirstNode){
                return ((CardGroupSectionFirstNode)(oldItem)).getCardGroup().getUpdate() == ((CardGroupSectionFirstNode)(newItem)).getCardGroup().getUpdate();
            }
            else if(oldItem instanceof CardGroupSectionSecondNode && newItem instanceof CardGroupSectionSecondNode){
                return ((CardGroupSectionSecondNode)(oldItem)).getSkillcard().second.equals(((CardGroupSectionSecondNode)(newItem)).getSkillcard().second);
            }
            return false;
        }
    }

}
