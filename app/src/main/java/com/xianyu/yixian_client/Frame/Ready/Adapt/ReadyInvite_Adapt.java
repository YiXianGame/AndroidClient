package com.xianyu.yixian_client.Frame.Ready.Adapt;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.yixian.material.Entity.User;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ReadyInvite_Adapt extends BaseQuickAdapter<User, ReadyInvite_Adapt.ViewHolder> implements LoadMoreModule {
    private final CompositeDisposable disposable = new CompositeDisposable();
    public Filter_BluePrint bluePrint = new Filter_BluePrint();
    private ReadyViewModel viewModel;
    public ReadyInvite_Adapt(ReadyViewModel viewModel){
        super(R.layout.ready_invite_item);
        setDiffCallback(new DiffCallBack());
        this.viewModel = viewModel;
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
            filters.sort((o1, o2) -> o1.getState().compareTo((User.State) o2.getState()));
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
        holder.active_text.setText(friend.getState().toString());
        holder.head_image.setImageResource(R.drawable.touxiang);
        if(friend.getState().equals(User.State.Leisure)){
            holder.invite_button.setVisibility(View.VISIBLE);
        }
        else holder.invite_button.setVisibility(View.INVISIBLE);
        holder.invite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(friend.getState().equals(User.State.Leisure)){
                    disposable.add(viewModel.inviteFriend(friend.getId()).subscribe(value->{
                        if(value){
                            MessageDialog.Dialog(getContext(),"[邀请成功]",friend.getNickname()+"已收到邀请信息");
                        }
                        else MessageDialog.Dialog(getContext(),"[邀请失败]",friend.getNickname()+"该玩家处于不可邀请状态");
                    }));
                }
                else MessageDialog.Dialog(getContext(),"[邀请失败]","该玩家处于不可邀请状态");
            }
        });
    }

    @Override
    public void onDetachedFromRecyclerView(@NotNull RecyclerView recyclerView) {
        disposable.clear();
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public static class ViewHolder extends BaseViewHolder {
        TextView nickname_text;
        TextView level_text;
        TextView active_text;
        ImageView head_image;
        Button invite_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_text = itemView.findViewById(R.id.nickname_text);
            level_text = itemView.findViewById(R.id.level);
            active_text = itemView.findViewById(R.id.active_text);
            head_image = itemView.findViewById(R.id.head_image);
            invite_button = itemView.findViewById(R.id.invite_button);
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
