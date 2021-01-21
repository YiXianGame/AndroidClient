package com.xianyu.yixian_client.Frame.BattleRepository.Adapt;

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

public class SkillCardAdapt extends RecyclerView.Adapter<SkillCardAdapt.ViewHolder> {
    private List<SkillCard> origin_data;
    private List<SkillCard> skillCards;
    public BluePrint bluePrint = new BluePrint();
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
        holder.buffs_recycle.setAdapter(new BuffAdapt(new ArrayList<>(skillCard.getBuffs().values())));
        holder.attributes_recycle.setAdapter(new AttributeAdapt(new ArrayList<>(skillCard.getAttributes().values())));
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
        MaterialTextView mp_text;
        RecyclerView buffs_recycle;
        RecyclerView attributes_recycle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            mp_text = itemView.findViewById(R.id.mp_num_text);
            buffs_recycle = itemView.findViewById(R.id.buffs_recycle);
            attributes_recycle = itemView.findViewById(R.id.attributes_recycle);
        }
    }
}
