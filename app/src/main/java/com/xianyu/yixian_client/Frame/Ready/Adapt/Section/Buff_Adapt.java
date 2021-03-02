package com.xianyu.yixian_client.Frame.Ready.Adapt.Section;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.R;
import com.yixian.material.Entity.Buff;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Buff_Adapt extends BaseQuickAdapter<Buff, Buff_Adapt.ViewHolder> {

    public Buff_Adapt() {
        super(R.layout.repository_buff_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder viewHolder, Buff buff) {
        if(buff.getCategory() == Buff.Category.Freeze){
            //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
            viewHolder.name_text.setText(String.format(Locale.getDefault(),"%s",viewHolder.name_text.getContext().getResources().getString(R.string.buff_freeze)));
            viewHolder.name_text.setTextColor(viewHolder.name_text.getContext().getResources().getColor(R.color.light_blue_400,viewHolder.name_text.getContext().getTheme()));
        }
    }


    public  class ViewHolder extends BaseViewHolder {
        MaterialTextView name_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
        }
    }

    public class DiffCallBack extends DiffUtil.ItemCallback<Buff>{
        @Override
        public boolean areItemsTheSame(@NonNull Buff oldItem, @NonNull Buff newItem) {
            return oldItem.getCategory().equals(newItem.getCategory());
        }
        @Override
        public boolean areContentsTheSame(@NonNull Buff oldItem, @NonNull Buff newItem) {
            return oldItem.getCategory().equals(newItem.getCategory());
        }
    }
}
