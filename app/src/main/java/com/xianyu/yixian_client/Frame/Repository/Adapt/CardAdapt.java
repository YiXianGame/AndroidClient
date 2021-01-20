package com.xianyu.yixian_client.Frame.Repository.Adapt;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.Attribute;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardAdapt extends RecyclerView.Adapter<CardAdapt.ViewHolder> {
    private List<SkillCard> origin_data;
    private ArrayList<SkillCard> skillCards;
    public  CardAdapt(List<SkillCard> origin_data){
        this.origin_data = origin_data;
        skillCards = new ArrayList<>(origin_data);
    }
    public BluePrint bluePrint = new BluePrint();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_card_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillCard skillCard = skillCards.get(position);
        holder.buffs_recycle.setAdapter(new BuffAdapt(new ArrayList<>(skillCard.getBuffs().values())));
        holder.attributes_recycle.setAdapter(new AttributeAdapt(new ArrayList<Attribute>(skillCard.getAttributes().values())));
        holder.name_text.setText(skillCard.getName());
        holder.description.setText(skillCard.getDescription());
        holder.mp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getMp()));
        holder.enemyHp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getEnemy_hp()));
        holder.enemyMp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getEnemy_mp()));
        holder.auxiliaryHp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getAuxiliary_hp()));
        holder.auxiliaryMp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getAuxiliary_mp()));
        holder.probability_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getProbability()));
        holder.max_enemy_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getMax_enemy()));
        holder.max_auxiliary.setText(String.format(Locale.getDefault(),"%d",skillCard.getMax_auxiliary()));
    }

    @Override
    public int getItemCount() {
            return skillCards.size();
    }
    public void setOrigin_data(List<SkillCard> origin_data){
        this.origin_data = origin_data;
        filter();
    }
    public void filter() {
        ArrayList<SkillCard> newValues = new ArrayList<>(origin_data);
        for (SkillCard value : origin_data) {
            if (!bluePrint.getName().equals("") && !value.getName().contains(bluePrint.getName())) {
                newValues.remove(value);
            } else if (bluePrint.isCure() && !value.getAttributes().containsKey(Attribute.Category.Cure)) {
                newValues.remove(value);
            } else if (bluePrint.isAttack() && !value.getAttributes().containsKey(Attribute.Category.Attack)) {
                newValues.remove(value);
            } else if (bluePrint.isEternal() && !value.getAttributes().containsKey(Attribute.Category.Eternal)) {
                newValues.remove(value);
            } else if (bluePrint.isMagic() && !value.getAttributes().containsKey(Attribute.Category.Magic)) {
                newValues.remove(value);
            } else if (bluePrint.isPhysics() && !value.getAttributes().containsKey(Attribute.Category.Physics)) {
                newValues.remove(value);
            }
        }
        skillCards = newValues;
        notifyDataSetChanged();
    }
    public class BluePrint{
        public String name = "";
        public boolean cure = false;
        public boolean attack = false;
        public boolean magic = false;
        public boolean physics = false;
        public boolean eternal = false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            filter();
        }

        public boolean isCure() {
            return cure;
        }

        public void setCure(boolean cure) {
            this.cure = cure;
            filter();
        }

        public boolean isAttack() {
            return attack;
        }

        public void setAttack(boolean attack) {
            this.attack = attack;
            filter();
        }

        public boolean isMagic() {
            return magic;
        }

        public void setMagic(boolean magic) {
            this.magic = magic;
            filter();
        }

        public boolean isPhysics() {
            return physics;
        }

        public void setPhysics(boolean physics) {
            this.physics = physics;
            filter();
        }

        public boolean isEternal() {
            return eternal;
        }

        public void setEternal(boolean eternal) {
            this.eternal = eternal;
            filter();
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView name_text;
        MaterialTextView description;
        MaterialTextView enemyHp_text;
        MaterialTextView enemyMp_text;
        MaterialTextView auxiliaryHp_text;
        MaterialTextView auxiliaryMp_text;
        MaterialTextView probability_text;
        MaterialTextView max_enemy_text;
        MaterialTextView max_auxiliary;
        MaterialTextView mp_text;
        RecyclerView attributes_recycle;
        RecyclerView buffs_recycle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            description =  itemView.findViewById(R.id.description);
            enemyHp_text = itemView.findViewById(R.id.enemyHp_num_text);
            enemyMp_text =itemView.findViewById(R.id.enemyMp_num_text);
            auxiliaryHp_text =itemView.findViewById(R.id.auxiliaryHp_num_text);
            auxiliaryMp_text =itemView.findViewById(R.id.auxiliaryMp_num_text);
            probability_text =itemView.findViewById(R.id.probability_num_text);
            max_enemy_text =itemView.findViewById(R.id.max_enemy_num_text);
            max_auxiliary =itemView.findViewById(R.id.max_auxiliary_num_text);
            mp_text =itemView.findViewById(R.id.mp_num_text);
            attributes_recycle = itemView.findViewById(R.id.attributes_recycle);
            buffs_recycle = itemView.findViewById(R.id.buffs_recycle);
        }
    }
}
