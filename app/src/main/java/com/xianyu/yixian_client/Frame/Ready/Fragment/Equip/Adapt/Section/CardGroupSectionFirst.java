package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt.Section;

import android.widget.Button;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.R;
import com.yixian.make.Core;

import org.jetbrains.annotations.NotNull;

public class CardGroupSectionFirst extends BaseNodeProvider {
    private ReadyViewModel viewModel;
    public CardGroupSectionFirst(ReadyViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ready_equip_group_first;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        MaterialTextView name_text = baseViewHolder.findView(R.id.name_text);
        name_text.setText(((CardGroupSectionFirstNode)baseNode).getCardGroup().getName());
        Button expand_button = baseViewHolder.findView(R.id.expand_button);
        expand_button.setOnClickListener(v -> {
            if(!viewModel.isConfirm()){
                viewModel.getPlayer().setCardGroup(((CardGroupSectionFirstNode) baseNode).cardGroup);
                viewModel.getEquipRequest().SwitchCardGroup(viewModel.getPlayer().getCardGroup());
            }
        });
    }
}
