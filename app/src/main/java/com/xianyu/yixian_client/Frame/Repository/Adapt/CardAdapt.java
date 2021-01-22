package com.xianyu.yixian_client.Frame.Repository.Adapt;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.Attribute;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardAdapt extends BaseQuickAdapter<SkillCard,CardAdapt.ViewHolder> implements LoadMoreModule {
    public BluePrint bluePrint = new BluePrint();
    public  CardAdapt(List<SkillCard> origin_data){
        super(R.layout.repository_card_item,origin_data);
        this.setDiffCallback(new DiffCallBack());
    }

    public void filter() {
        List<SkillCard> origin_data = null;
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
        //skillCards = newValues;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NotNull ViewHolder viewHolder, SkillCard skillCard) {
        BuffAdapt buff_adapter = new BuffAdapt();
        buff_adapter.setDiffNewData(new ArrayList<>(skillCard.getBuffs().values()));
        viewHolder.buffs_recycle.setAdapter(buff_adapter);
        AttributeAdapt attribute_adapt = new AttributeAdapt();
        attribute_adapt.setDiffNewData(new ArrayList<>(skillCard.getAttributes().values()));
        viewHolder.attributes_recycle.setAdapter(attribute_adapt);
        viewHolder.name_text.setText(skillCard.getName());
        viewHolder.description.setText(skillCard.getDescription());
        viewHolder.mp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getMp()));
        viewHolder.enemyHp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getEnemy_hp()));
        viewHolder.enemyMp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getEnemy_mp()));
        viewHolder.auxiliaryHp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getAuxiliary_hp()));
        viewHolder.auxiliaryMp_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getAuxiliary_mp()));
        viewHolder.probability_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getProbability()));
        viewHolder.max_enemy_text.setText(String.format(Locale.getDefault(),"%d",skillCard.getMax_enemy()));
        viewHolder.max_auxiliary.setText(String.format(Locale.getDefault(),"%d",skillCard.getMax_auxiliary()));
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
    public class ViewHolder extends BaseViewHolder {
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
    protected class DiffCallBack extends DiffUtil.ItemCallback<SkillCard>{
        @Override
        public boolean areItemsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
            return oldItem.getUpdate() == newItem.getUpdate();
        }
    }
}
