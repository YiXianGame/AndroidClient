package com.xianyu.yixian_client.Frame.BattleRepository.Adapt;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SkillCardAdapt extends BaseQuickAdapter<SkillCard, SkillCardAdapt.ViewHolder > implements LoadMoreModule{
    public BluePrint bluePrint = new BluePrint();
    public List<SkillCard> skillCards_filters;
    public SkillCardAdapt(){
        super(R.layout.battle_repository_card_item);
        setDiffCallback(new DiffCallBack());
    }
    @Override
    protected void convert(@NotNull SkillCardAdapt.ViewHolder holder, SkillCard skillCard) {
        holder.name_text.setText(skillCard.getName());
        holder.mp_text.setText(String.format(Locale.CHINESE,"%d",skillCard.getMp()));
        BuffAdapt auxiliary_buff_adapt = new BuffAdapt();
        auxiliary_buff_adapt.setDiffNewData(new ArrayList<>(skillCard.getAuxiliary_buffs().values()));
        holder.auxiliary_buffs_recycle.setAdapter(auxiliary_buff_adapt);

        BuffAdapt enemy_buff_adapt = new BuffAdapt();
        enemy_buff_adapt.setDiffNewData(new ArrayList<>(skillCard.getEnemy_buffs().values()));
        holder.enemy_buffs_recycle.setAdapter(enemy_buff_adapt);

        AttributeAdapt attribute_adapt = new AttributeAdapt();
        attribute_adapt.setDiffNewData(new ArrayList<>(skillCard.getAttributes().values()));
        holder.attributes_recycle.setAdapter(attribute_adapt);
    }
    public void filter(List<SkillCard> origin_data) {
        getData().removeAll(getData());
        notifyDataSetChanged();
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
        skillCards_filters = newValues;
        if(skillCards_filters.size() >= 9){
            setDiffNewData(new ArrayList<>(skillCards_filters.subList(0,10)));
        }
        else {
            setDiffNewData(new ArrayList<>(skillCards_filters));
        }
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
        }

        public boolean isCure() {
            return cure;
        }

        public void setCure(boolean cure) {
            this.cure = cure;
        }

        public boolean isAttack() {
            return attack;
        }

        public void setAttack(boolean attack) {
            this.attack = attack;
        }

        public boolean isMagic() {
            return magic;
        }

        public void setMagic(boolean magic) {
            this.magic = magic;
        }

        public boolean isPhysics() {
            return physics;
        }

        public void setPhysics(boolean physics) {
            this.physics = physics;
        }

        public boolean isEternal() {
            return eternal;
        }

        public void setEternal(boolean eternal) {
            this.eternal = eternal;
        }
    }
    public static class ViewHolder extends BaseViewHolder {
        MaterialTextView name_text;
        MaterialTextView mp_text;
        RecyclerView attributes_recycle;
        RecyclerView enemy_buffs_recycle;
        RecyclerView auxiliary_buffs_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            mp_text = itemView.findViewById(R.id.mp_num_text);
            auxiliary_buffs_recycle = itemView.findViewById(R.id.auxiliary_buffs_recycle);
            enemy_buffs_recycle = itemView.findViewById(R.id.enemy_buffs_recycle);
            attributes_recycle = itemView.findViewById(R.id.attributes_recycle);
        }
    }
    protected class DiffCallBack extends DiffUtil.ItemCallback<SkillCard>{
        @Override
        public boolean areItemsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
            return oldItem.getAttributeUpdate() == newItem.getAttributeUpdate();
        }
    }
}
