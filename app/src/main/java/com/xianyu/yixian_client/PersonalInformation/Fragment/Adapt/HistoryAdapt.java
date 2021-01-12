package com.xianyu.yixian_client.PersonalInformation.Fragment.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.Repository.CardAdapt;

import java.util.List;

public class HistoryAdapt extends RecyclerView.Adapter<HistoryAdapt.ViewHolder> {
    private List<History> histories;
    public  HistoryAdapt(List<History> histories){
        this.histories = histories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_information_history_item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //这个position位置的数据应该怎么和UI进行适配
        if(histories.get(position).isResult()){
            holder.result_text.setText(R.string.victory);
        }
        else holder.result_text.setText(R.string.defeat);
        holder.kills_text.setText(Integer.toString(histories.get(position).getKills()));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView result_text;
        MaterialTextView kills_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result_text = itemView.findViewById(R.id.result_text);
            kills_text = itemView.findViewById(R.id.kills_text);
        }
    }
}
