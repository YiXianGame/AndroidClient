package com.xianyu.yixian_client.Frame.Main.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Friend_Adapt extends RecyclerView.Adapter<Friend_Adapt.ViewHolder> {
    List<User> origin_data;
    ArrayList<User> filter_data;
    public Filter_BluePrint bluePrint = new Filter_BluePrint();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    public Friend_Adapt(List<User> list){
        origin_data = list;
        filter_data = new ArrayList<User>(list);
    }
    public void refreshData(List<User> list){
        origin_data = list;
        filter_data = new ArrayList<>(list);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
        User friend = filter_data.get(position);
        holder.nickname_text.setText(friend.getNickName());
        holder.level_text.setText(Integer.toString(friend.getLv()));
        holder.active_text.setText(friend.getActive().toString());
    }

    @Override
    public int getItemCount() {
        return filter_data.size();
    }

    protected void Filter() {
        ArrayList<User> friends = new ArrayList<>();
        for(User item : origin_data){
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
        filter_data = friends;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
            Filter();
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
            Filter();
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
            Filter();
        }

        public boolean isLevel() {
            return level;
        }

        public void setLevel(boolean level) {
            this.level = level;
            Filter();
        }
    }
}
