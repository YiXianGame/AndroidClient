package com.xianyu.yixian_client.BattleRepository;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;


import java.util.ArrayList;
import java.util.List;

public class CardAdapt extends RecyclerView.Adapter<CardAdapt.ViewHolder>{
    private List<SkillCard> simple_skillCards;
    public ArrayList<String> data =new ArrayList<String>();
    public CardAdapt(List<SkillCard> simple_skillCards){
        this.simple_skillCards = simple_skillCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.battlerepositorycard_item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
        SkillCard skillCard = simple_skillCards.get(position);
        holder.skill.setText(skillCard.getName());
    }

    @Override
    public int getItemCount() {
        return simple_skillCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView skill;
        MaterialTextView skill1;
        MaterialTextView skill2;
        MaterialTextView skill3;
        MaterialTextView skill7;
        MaterialTextView skill4;
        MaterialTextView skill5;
        MaterialTextView skill6;
        MaterialTextView skill8;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skill=itemView.findViewById(R.id.skill);
            skill=itemView.findViewById(R.id.skill1);
            skill=itemView.findViewById(R.id.skill2);
            skill=itemView.findViewById(R.id.skill3);
            skill=itemView.findViewById(R.id.skill4);
            skill=itemView.findViewById(R.id.skill5);
            skill=itemView.findViewById(R.id.skill6);
            skill=itemView.findViewById(R.id.skill7);
            skill=itemView.findViewById(R.id.skill8);
        }
    }
}
