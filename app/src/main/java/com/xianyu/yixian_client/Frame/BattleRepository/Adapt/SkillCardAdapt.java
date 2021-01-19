package com.xianyu.yixian_client.Frame.BattleRepository.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SkillCardAdapt extends RecyclerView.Adapter<SkillCardAdapt.ViewHolder> {
    private List<SkillCard> origin_data;
    private List<SkillCard> skillCards;
    public SkillCard bluePrint = new SkillCard();
    public SkillCardAdapt(List<SkillCard> skillCards){
        this.origin_data = skillCards;
        this.skillCards = new ArrayList<>(origin_data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.battle_repository_card_item,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }
    public void refresh(List<SkillCard> skillCards){
        origin_data = skillCards;
        filter();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
        SkillCard skillCard = skillCards.get(position);
        holder.name_text.setText(skillCard.getName());
        holder.mp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getMp()));
        if(skillCard.isPhysics())holder.physics_text.setVisibility(View.VISIBLE);
        else holder.physics_text.setVisibility(View.INVISIBLE);
        if(skillCard.isMagic())holder.magic_text.setVisibility(View.VISIBLE);
        else holder.magic_text.setVisibility(View.INVISIBLE);
        if(skillCard.isEternal())holder.eternal_text.setVisibility(View.VISIBLE);
        else holder.eternal_text.setVisibility(View.INVISIBLE);
        if(skillCard.isAttack())holder.attack_text.setVisibility(View.VISIBLE);
        else holder.attack_text.setVisibility(View.INVISIBLE);
        if(skillCard.isCure())holder.cure_text.setVisibility(View.VISIBLE);
        else holder.cure_text.setVisibility(View.INVISIBLE);
        holder.enemyHp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getEnemy_hp()));
        holder.enemyMp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getEnemy_mp()));
        holder.auxiliaryHp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getAuxiliary_hp()));
        holder.auxiliaryMp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getAuxiliary_mp()));
        holder.probability_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getProbability()));
        holder.maxEnemy_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getMax_enemy()));
        holder.maxAuxiliary_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getMax_auxiliary()));
        holder.bottom_layout.setAdapter(new BuffAdapt(skillCard.getBuffs()));
    }

    @Override
    public int getItemCount() {
        if(skillCards != null)return skillCards.size();
        else return 0;
    }

    public void filter() {
        ArrayList<SkillCard> newValues = new ArrayList<>(origin_data);
        for (SkillCard value : origin_data) {
            if (!bluePrint.getName().equals("") && !value.getName().contains(bluePrint.getName())) {
                newValues.remove(value);
            } else if (bluePrint.isCure() && !value.isCure()) {
                newValues.remove(value);
            } else if (bluePrint.isAttack() && !value.isAttack()) {
                newValues.remove(value);
            } else if (bluePrint.isEternal() && !value.isEternal()) {
                newValues.remove(value);
            } else if (bluePrint.isMagic() && !value.isMagic()) {
                newValues.remove(value);
            } else if (bluePrint.isPhysics() && !value.isPhysics()) {
                newValues.remove(value);
            }
        }
        skillCards = newValues;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView name_text;
        MaterialTextView mp_text;
        MaterialTextView physics_text;
        MaterialTextView magic_text;
        MaterialTextView cure_text;
        MaterialTextView attack_text;
        MaterialTextView eternal_text;
        MaterialTextView enemyHp_text;
        MaterialTextView enemyMp_text;
        MaterialTextView auxiliaryHp_text;
        MaterialTextView auxiliaryMp_text;
        MaterialTextView probability_text;
        MaterialTextView maxEnemy_text;
        MaterialTextView maxAuxiliary_text;
        RecyclerView bottom_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            mp_text = itemView.findViewById(R.id.mp_text);
            physics_text = itemView.findViewById(R.id.physics_text);
            magic_text = itemView.findViewById(R.id.magic_text);
            cure_text = itemView.findViewById(R.id.cure_text);
            attack_text = itemView.findViewById(R.id.attack_text);
            eternal_text = itemView.findViewById(R.id.eternal_text);
            enemyHp_text = itemView.findViewById(R.id.enemy_hp_text);
            enemyMp_text = itemView.findViewById(R.id.enemy_mp_text);
            auxiliaryMp_text = itemView.findViewById(R.id.auxiliary_mp_text);
            auxiliaryHp_text = itemView.findViewById(R.id.auxiliary_hp_text);
            probability_text = itemView.findViewById(R.id.probability_text);
            maxEnemy_text = itemView.findViewById(R.id.max_enemy_text);
            maxAuxiliary_text = itemView.findViewById(R.id.max_auxiliary_text);
            bottom_layout = itemView.findViewById(R.id.bottom_layout);
        }
    }
}
