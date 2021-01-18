package com.xianyu.yixian_client.Frame.Repository;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import java.util.List;

public class CardAdapt extends RecyclerView.Adapter<CardAdapt.ViewHolder> {
    private List<SkillCard> skillCards;
    public  CardAdapt(List<SkillCard> skillCards){
        this.skillCards = skillCards;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_card_item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillCard skillCard = skillCards.get(position);
        holder.buffs_text.setText("");
        StringBuilder stringBuilder = new StringBuilder();

        for(Buff item : skillCard.getBuffs()){
            stringBuilder.append(item.getName() + " ");
        }
        holder.buffs_text.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
        MaterialTextView materialTextView;

        if(skillCard.isAttack()){
            materialTextView = new MaterialTextView(holder.attributes_layout.getContext());
            materialTextView.setText("攻击");
            materialTextView.setTextColor(0xFFE91E63);
            materialTextView.setPadding(0,0,15,0);
            holder.attributes_layout.addView(materialTextView);
        }
        if(skillCard.isCure()){
            materialTextView = new MaterialTextView(holder.attributes_layout.getContext());
            materialTextView.setText("治疗");
            materialTextView.setTextColor(0xFF4CAF50);
            materialTextView.setPadding(0,0,15,0);
            holder.attributes_layout.addView(materialTextView);
        }
        if(skillCard.isMagic()){
            materialTextView = new MaterialTextView(holder.attributes_layout.getContext());
            materialTextView.setText("魔法");
            materialTextView.setTextColor(0xFF0288d1);
            materialTextView.setPadding(0,0,15,0);
            holder.attributes_layout.addView(materialTextView);
        }
        if(skillCard.isPhysics()){
            materialTextView = new MaterialTextView(holder.attributes_layout.getContext());
            materialTextView.setText("物理");
            materialTextView.setTextColor(0xFF3700B3);
            materialTextView.setPadding(0,0,15,0);
            holder.attributes_layout.addView(materialTextView);
        }
        if(skillCard.isEternal()){
            materialTextView = new MaterialTextView(holder.attributes_layout.getContext());
            materialTextView.setText("魔法");
            materialTextView.setTextColor(0xFFFFFF00);
            holder.attributes_layout.addView(materialTextView);
        }

        holder.name_text.setText(skillCard.getName());
        holder.description.setText(skillCard.getDescription());
        holder.enemyHp_text.setText(Integer.toString(skillCard.getEnemy_hp()));
        holder.damage_Mp_text.setText(Integer.toString(skillCard.getEnemy_mp()));
        holder.cure_text.setText(Integer.toString(skillCard.getAuxiliary_hp()));
        holder.recover_Mp_text.setText(Integer.toString(skillCard.getAuxiliary_mp()));
        holder.probability_text.setText(Integer.toString(skillCard.getProbability()));
        holder.attack_Number_text.setText(Integer.toString(skillCard.getMax_enemy()));
        holder.auxiliary_Number_text.setText(Integer.toString(skillCard.getMax_auxiliary()));
        holder.need_Mp_text.setText(Integer.toString(skillCard.getMp()));
    }

    @Override
    public int getItemCount() {
            return skillCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView name_text;
        LinearLayout attributes_layout;
        MaterialTextView buffs_text;
        MaterialTextView description;
        MaterialTextView enemyHp_text;
        MaterialTextView damage_Mp_text;
        MaterialTextView cure_text;
        MaterialTextView recover_Mp_text;
        MaterialTextView probability_text;
        MaterialTextView attack_Number_text;
        MaterialTextView auxiliary_Number_text;
        MaterialTextView need_Mp_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            buffs_text = itemView.findViewById(R.id.buffs_text);
            attributes_layout = itemView.findViewById(R.id.attributes_layout);
            description =  itemView.findViewById(R.id.description);
            enemyHp_text = itemView.findViewById(R.id.enemyHp_text);
            damage_Mp_text =itemView.findViewById(R.id.damage_Mp_text);
            cure_text =itemView.findViewById(R.id.cure_text);
            recover_Mp_text =itemView.findViewById(R.id.recover_Mp_text);
            probability_text =itemView.findViewById(R.id.probability_text);
            attack_Number_text =itemView.findViewById(R.id.attack_Number_text);
            auxiliary_Number_text =itemView.findViewById(R.id.auxiliary_Number_text);
            need_Mp_text =itemView.findViewById(R.id.need_Mp_text);
        }
    }
}
