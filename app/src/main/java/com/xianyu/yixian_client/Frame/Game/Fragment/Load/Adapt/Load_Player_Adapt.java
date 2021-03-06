package com.xianyu.yixian_client.Frame.Game.Fragment.Load.Adapt;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.R;
import com.yixian.material.Entity.Player;

import org.jetbrains.annotations.NotNull;

public class Load_Player_Adapt extends BaseQuickAdapter<Player,Load_Player_Adapt.ViewHolder>{
    public Load_Player_Adapt(){
        super(R.layout.friend_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder viewHolder, Player player) {
        viewHolder.name_text.setText(player.getNickname());
    }


    public static class ViewHolder extends BaseViewHolder {
        TextView name_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
    protected class DiffCallBack extends DiffUtil.ItemCallback<Player>{
        @Override
        public boolean areItemsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return false;
        }
    }
}
