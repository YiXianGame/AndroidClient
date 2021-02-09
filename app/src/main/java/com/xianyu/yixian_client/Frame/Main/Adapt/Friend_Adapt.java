package com.xianyu.yixian_client.Frame.Main.Adapt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yixian.material.Entity.User;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Friend_Adapt extends BaseQuickAdapter<User,Friend_Adapt.ViewHolder> implements LoadMoreModule {
    public Filter_BluePrint bluePrint = new Filter_BluePrint();
    public Friend_Adapt(){
        super(R.layout.main_friend_item);
        setDiffCallback(new DiffCallBack());
    }
    public List<User> filter(List<User> friends) {
        ArrayList<User> filters = new ArrayList<>();
        for(User item : friends){
            if(item.getNickname().contains(bluePrint.getNickName()) || bluePrint.getNickName().equals(""))filters.add(item);
        }
        //sort遵循稳定排序规则
        if(bluePrint.isLevel()){
            filters.sort((o1, o2) -> o1.getLv() - o2.getLv());
        }
        if(bluePrint.isActive()){
            filters.sort((o1, o2) -> o1.getActive().compareTo((User.State) o2.getActive()));
        }
        if(bluePrint.isReverse()){
            Collections.reverse(filters);
        }
        return filters;
    }
    @Override
    protected void convert(@NotNull ViewHolder holder, User friend) {
        holder.nickname_text.setText(friend.getNickname());
        holder.level_text.setText(Integer.toString(friend.getLv()));
        holder.active_text.setText(friend.getActive().toString());
        holder.head_image.setImageResource(R.drawable.touxiang);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView nickname_text;
        TextView level_text;
        TextView active_text;
        ImageView head_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname_text);
            level_text = itemView.findViewById(R.id.level);
            active_text = itemView.findViewById(R.id.active_text);
            head_image = itemView.findViewById(R.id.head_image);
        }
    }
    public class Filter_BluePrint{
        String nickName = "";
        boolean active = false;
        boolean level = false;
        boolean reverse = false;

        public boolean isReverse() {
            return reverse;
        }

        public void setReverse(boolean reverse) {
            this.reverse = reverse;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isLevel() {
            return level;
        }

        public void setLevel(boolean level) {
            this.level = level;
        }
    }
    protected class DiffCallBack extends DiffUtil.ItemCallback<User>{
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return (oldItem.getAttribute_update() == newItem.getAttribute_update() && oldItem.getHeadImage_update() == newItem.getHeadImage_update());
        }
    }
}
