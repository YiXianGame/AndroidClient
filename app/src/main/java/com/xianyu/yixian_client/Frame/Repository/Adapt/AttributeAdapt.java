package com.xianyu.yixian_client.Frame.Repository.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.Attribute;
import com.xianyu.yixian_client.R;

import java.util.List;
import java.util.Locale;

public class AttributeAdapt extends RecyclerView.Adapter<AttributeAdapt.ViewHolder>{
    private List<Attribute> attributes;
    public AttributeAdapt(List<Attribute> attributes){
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_attribute_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attribute.Category category = attributes.get(position).getCategory();
        //用于对RecyclerView的子项进行赋值，会在每个子项滚动到屏幕内的时候执行
        if(category == Attribute.Category.Attack){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_attack)));
        }
        else if(category == Attribute.Category.Cure){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_cure)));
        }
        else if(category == Attribute.Category.Physics){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_physics)));
        }
        else if(category == Attribute.Category.Magic){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_magic)));
        }
        else if(category == Attribute.Category.Eternal){
            holder.name_text.setText(String.format(Locale.getDefault(),"%s",holder.name_text.getContext().getResources().getString(R.string.attribute_eternal)));
        }
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView name_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
}
