package com.xianyu.yixian_client.Frame.FriendSystem.Adapt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Friend_Adapt extends BaseQuickAdapter<User,Friend_Adapt.ViewHolder> implements LoadMoreModule {
    public List<User> friends_filters;
    public Filter_BluePrint bluePrint = new Filter_BluePrint();
    public Friend_Adapt(){
        super(R.layout.friend_item);
        setDiffCallback(new DiffCallBack());
    }
    public void filter(List<User> friends) {
        ArrayList<User> filters = new ArrayList<>();
        for(User item : friends){
            if(item.getNickName().contains(bluePrint.getNickName()) || bluePrint.getNickName().equals(""))filters.add(item);
        }
        //sort遵循稳定排序规则
        if(bluePrint.isLevel()){
            filters.sort((o1, o2) -> o1.getLv() - o2.getLv());
        }
        if(bluePrint.isActive()){
            filters.sort((o1, o2) -> o1.getActive().compareTo((User.State) o2.getActive()));
        }
        setDiffNewData(filters);
        friends_filters = filters;
        if(friends_filters.size() >= 9){
            setDiffNewData(new ArrayList<>(friends_filters.subList(0,10)));
        }
        else {
            setDiffNewData(new ArrayList<>(friends_filters));
        }
    }
    @Override
    protected void convert(@NotNull ViewHolder holder, User friend) {
        holder.nickname_text.setText(friend.getNickName());
        holder.level_text.setText(Integer.toString(friend.getLv()));
        holder.deleteFriend_button.setOnClickListener(v -> {
            remove(friend);//这步没有同步到数据库
        });
        holder.active_text.setText(friend.getActive().toString());
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView nickname_text;
        TextView level_text;
        TextView active_text;
        Button deleteFriend_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname_text);
            level_text = itemView.findViewById(R.id.level);
            active_text = itemView.findViewById(R.id.active_text);
            deleteFriend_button = itemView.findViewById(R.id.deleteFriend_button);
        }
    }
    public class Filter_BluePrint{
        String nickName = "";
        boolean active = false;
        boolean level = false;

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
