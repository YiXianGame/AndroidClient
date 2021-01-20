package com.xianyu.yixian_client.Frame.Repository.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.R;

import java.util.List;
import java.util.Locale;

public class BuffAdapt extends RecyclerView.Adapter<BuffAdapt.ViewHolder> {
    private List<Buff> buffs;
    public BuffAdapt(List<Buff> buffs){
        this.buffs = buffs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_buff_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Buff.Category category = buffs.get(position).getCategory();
        if(category == Buff.Category.Freeze){
            //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.buff_freeze)));
        }
    }

    @Override
    public int getItemCount() {
        return buffs.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
}
