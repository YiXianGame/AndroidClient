package com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section;

import android.widget.Button;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

public class CardGroupSectionFirst extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.battle_repository_group_first;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        MaterialTextView name_text = baseViewHolder.findView(R.id.name_text);
        name_text.setText(((CardGroupSectionFirstNode)baseNode).getCardGroup().getName());
    }
}
