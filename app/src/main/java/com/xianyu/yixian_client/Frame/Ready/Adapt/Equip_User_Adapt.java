package com.xianyu.yixian_client.Frame.Ready.Adapt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Equip_User_Adapt extends BaseQuickAdapter<UserWithCardGroupItem, Equip_User_Adapt.ViewHolder> {
    private ReadyViewModel viewModel;
    public Equip_User_Adapt(ReadyViewModel viewModel){
        super(R.layout.ready_user_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder holder, UserWithCardGroupItem user) {
        if(user.getUser().getHeadImage()!=null)Glide.with(holder.itemView).load(user.getUser().getHeadImage()).into(holder.head_image);
        else holder.head_image.setImageResource(R.drawable.touxiang);
        holder.nickname_text.setText(user.getUser().getNickname());
        holder.cardGroup_name_text.setText(String.format(Locale.getDefault(),"%s",user.getCardGroup().getName()));
        holder.active_text.setText(user.getUser().getState().toString());
    }

    protected class DiffCallBack extends DiffUtil.ItemCallback<UserWithCardGroupItem>{
        @Override
        public boolean areItemsTheSame(@NonNull UserWithCardGroupItem oldItem, @NonNull UserWithCardGroupItem newItem) {
            return oldItem.getUser().getId() == newItem.getUser().getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull UserWithCardGroupItem oldItem, @NonNull UserWithCardGroupItem newItem) {
            return oldItem.getUser().getAttribute_update() == newItem.getUser().getAttribute_update();
        }
    }
    public class ViewHolder extends BaseViewHolder {
        TextView nickname_text;
        TextView cardGroup_name_text;
        TextView active_text;
        ImageView head_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname_text);
            cardGroup_name_text = itemView.findViewById(R.id.cardGroup_name_text);
            active_text = itemView.findViewById(R.id.active_text);
            head_image = itemView.findViewById(R.id.head_image);
        }
    }
}
