package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.R;
import com.yixian.material.Entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Equip_User_Adapt extends BaseQuickAdapter<Player, Equip_User_Adapt.ViewHolder> {
    private ReadyViewModel viewModel;
    public Equip_User_Adapt(ReadyViewModel viewModel){
        super(R.layout.ready_equip_user_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder holder, Player user) {
        if(user.getHeadImage()!=null)Glide.with(holder.itemView).load(user.getHeadImage()).into(holder.head_image);
        else holder.head_image.setImageResource(R.drawable.touxiang);
        holder.nickname_text.setText(user.getNickname());
        if(user.getCardGroup()!=null)holder.cardGroup_name_text.setText(String.format(Locale.getDefault(),"%s",user.getCardGroup().getName()));
    }

    protected class DiffCallBack extends DiffUtil.ItemCallback<Player>{
        @Override
        public boolean areItemsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return (oldItem.getCardGroup()!=newItem.getCardGroup());
        }
    }
    public class ViewHolder extends BaseViewHolder {
        TextView nickname_text;
        TextView cardGroup_name_text;
        ImageView head_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname_text);
            cardGroup_name_text = itemView.findViewById(R.id.cardGroup_name_text);
            head_image = itemView.findViewById(R.id.head_image);
        }
    }
}
