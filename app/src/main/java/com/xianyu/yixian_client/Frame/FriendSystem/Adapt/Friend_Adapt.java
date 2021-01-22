package com.xianyu.yixian_client.Frame.FriendSystem.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Friend_Adapt extends BaseQuickAdapter<User,Friend_Adapt.ViewHolder> implements LoadMoreModule {
    public Filter_BluePrint bluePrint = new Filter_BluePrint();
    public Friend_Adapt(){
        super(R.layout.friend_item);
        setDiffCallback(new DiffCallBack());
    }
    public List<User> filter(List<User> friends) {
        for(User item : friends){
            if(item.getNickName().contains(bluePrint.getNickName()) || bluePrint.getNickName().equals(""))friends.add(item);
        }
        //sort遵循稳定排序规则
        if(bluePrint.isLevel()){
            friends.sort((o1, o2) -> o1.getLv() - o2.getLv());
        }
        if(bluePrint.isActive()){
            friends.sort((o1, o2) -> o1.getActive().compareTo((User.State) o2.getActive()));
        }
        if(bluePrint.isReverse()){
            Collections.reverse(friends);
        }
        return friends;
    }
    @Override
    protected void convert(@NotNull ViewHolder holder, User friend) {
        holder.nickname_text.setText(friend.getNickName());
        holder.level_text.setText(Integer.toString(friend.getLv()));
        holder.deleteFriend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(friend);//这步没有同步到数据库
            }
        });
        holder.active_text.setText(friend.getActive().toString());
    }

    public static class ViewHolder extends BaseViewHolder {
        static int d;
        TextView nickname_text;
        TextView level_text;
        TextView active_text;
        Button deleteFriend_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname);
            level_text = itemView.findViewById(R.id.level);
            active_text = itemView.findViewById(R.id.active_text);
            deleteFriend_button = itemView.findViewById(R.id.deleteFriend_button);
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
            return oldItem.getUpdate() == newItem.getUpdate();
        }
    }
}
