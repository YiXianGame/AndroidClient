package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt.Section;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.R;
import com.yixian.material.Entity.SkillCard;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Attribute_Adapt extends BaseQuickAdapter<SkillCard.Category, Attribute_Adapt.ViewHolder> {


    public Attribute_Adapt() {
        super(R.layout.repository_attribute_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder holder, SkillCard.Category category) {
        //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
        if(category == SkillCard.Category.Attack){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_attack)));
            holder.name_text.setTextColor(holder.name_text.getContext().getResources().getColor(R.color.attack,holder.name_text.getContext().getTheme()));
        }
        else if(category == SkillCard.Category.Cure){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_cure)));
            holder.name_text.setTextColor(holder.name_text.getContext().getResources().getColor(R.color.cure,holder.name_text.getContext().getTheme()));
        }
        else if(category == SkillCard.Category.Physics){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_physics)));
            holder.name_text.setTextColor(holder.name_text.getContext().getResources().getColor(R.color.physics,holder.name_text.getContext().getTheme()));
        }
        else if(category == SkillCard.Category.Magic){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_magic)));
            holder.name_text.setTextColor(holder.name_text.getContext().getResources().getColor(R.color.magic,holder.name_text.getContext().getTheme()));
        }
        else if(category == SkillCard.Category.Eternal){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_eternal)));
            holder.name_text.setTextColor(holder.name_text.getContext().getResources().getColor(R.color.eternal,holder.name_text.getContext().getTheme()));
        }
    }


    public class ViewHolder extends BaseViewHolder {
        MaterialTextView name_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
    public class DiffCallBack extends DiffUtil.ItemCallback<SkillCard.Category>{
        @Override
        public boolean areItemsTheSame(@NonNull SkillCard.Category oldItem, @NonNull SkillCard.Category newItem) {
            return oldItem.equals(newItem);
        }
        @Override
        public boolean areContentsTheSame(@NonNull SkillCard.Category oldItem, @NonNull SkillCard.Category newItem) {
            return oldItem.equals(newItem);
        }
    }
}
