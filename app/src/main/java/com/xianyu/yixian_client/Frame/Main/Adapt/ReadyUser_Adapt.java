package com.xianyu.yixian_client.Frame.Main.Adapt;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yixian.material.Entity.User;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

public class ReadyUser_Adapt extends BaseQuickAdapter<User,ReadyUser_Adapt.ViewHolder> {
    public ReadyUser_Adapt(){
        super(R.layout.main_ready_user_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder viewHolder, User user) {
        Glide.with(viewHolder.itemView).load(user.getHeadImage()).into(viewHolder.head_image);
    }

    protected class DiffCallBack extends DiffUtil.ItemCallback<User>{
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getAttribute_update() == newItem.getAttribute_update();
        }
    }
    public class ViewHolder extends BaseViewHolder {
        ImageView head_image;
        Button change_pos_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head_image = itemView.findViewById(R.id.head_image);
            change_pos_button = itemView.findViewById(R.id.change_pos_button);
        }
    }
}
