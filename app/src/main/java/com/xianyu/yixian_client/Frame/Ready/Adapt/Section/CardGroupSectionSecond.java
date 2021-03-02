package com.xianyu.yixian_client.Frame.Ready.Adapt.Section;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.R;
import com.yixian.material.Entity.SkillCard;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class CardGroupSectionSecond extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ready_equip_group_second;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        SkillCard skillCard = ((CardGroupSectionSecondNode) baseNode).skillcard;
        MaterialTextView name_text;
        MaterialTextView mp_text;
        RecyclerView attributes_recycle;
        RecyclerView enemy_buffs_recycle;
        RecyclerView auxiliary_buffs_recycle;
        name_text = baseViewHolder.findView(R.id.name_text);
        mp_text = baseViewHolder.findView(R.id.mp_num_text);
        auxiliary_buffs_recycle = baseViewHolder.findView(R.id.auxiliary_buffs_recycle);
        enemy_buffs_recycle = baseViewHolder.findView(R.id.enemy_buffs_recycle);
        attributes_recycle = baseViewHolder.findView(R.id.attributes_recycle);
        name_text.setText(skillCard.getName());
        mp_text.setText(String.format(Locale.CHINESE,"%d", skillCard.getMp()));
        Buff_Adapt auxiliary_buff_adapt = new Buff_Adapt();
        auxiliary_buff_adapt.setDiffNewData(new ArrayList<>(skillCard.getAuxiliaryBuff()));
        auxiliary_buffs_recycle.setAdapter(auxiliary_buff_adapt);

        Buff_Adapt enemy_buff_adapt = new Buff_Adapt();
        enemy_buff_adapt.setDiffNewData(new ArrayList<>(skillCard.getEnemyBuff()));
        enemy_buffs_recycle.setAdapter(enemy_buff_adapt);

        Attribute_Adapt attribute_adapt = new Attribute_Adapt();
        attribute_adapt.setDiffNewData(new ArrayList<>(skillCard.getCategory()));
        attributes_recycle.setAdapter(attribute_adapt);
    }
}
