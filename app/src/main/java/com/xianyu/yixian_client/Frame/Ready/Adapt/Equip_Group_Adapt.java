package com.xianyu.yixian_client.Frame.Ready.Adapt;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.module.DraggableModule;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Section.CardGroupSectionFirst;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Section.CardGroupSectionSecond;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Section.CardGroupSectionSecondNode;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.yixian.material.Entity.CardGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Equip_Group_Adapt extends BaseNodeAdapter implements DraggableModule {
    private ArrayList<CardGroup> groups;
    public Equip_Group_Adapt(ReadyViewModel viewModel) {
        super();
        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new CardGroupSectionFirst(viewModel));
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
                return ((CardGroupSectionFirstNode) (oldItem)).getCardGroup().getName().equals(((CardGroupSectionFirstNode) (newItem)).getCardGroup().getName());
            }
            else if(oldItem instanceof CardGroupSectionSecondNode && newItem instanceof CardGroupSectionSecondNode){
                return ((CardGroupSectionSecondNode)(oldItem)).getSkillcard().getId() == ((CardGroupSectionSecondNode)(newItem)).getSkillcard().getId();
            }
            return false;
        }
        @Override
        public boolean areContentsTheSame(@NonNull BaseNode oldItem, @NonNull BaseNode newItem) {
            if(oldItem instanceof CardGroupSectionFirstNode && newItem instanceof CardGroupSectionFirstNode){
                return ((CardGroupSectionFirstNode)(oldItem)).getCardGroup().getName().equals(((CardGroupSectionFirstNode)(newItem)).getCardGroup().getName());
            }
            else if(oldItem instanceof CardGroupSectionSecondNode && newItem instanceof CardGroupSectionSecondNode){
                return ((CardGroupSectionSecondNode)(oldItem)).getSkillcard().getId() == ((CardGroupSectionSecondNode)(newItem)).getSkillcard().getId();
            }
            return false;
        }
    }

}
