package com.xianyu.yixian_client.Repository;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;

import java.util.List;

public class CardAdapt extends RecyclerView.Adapter<CardAdapt.ViewHolder> {
    private List<SkillCard> simple_skillCards;
    public  CardAdapt(List<SkillCard> simple_skillCards){
        this.simple_skillCards = simple_skillCards;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //用来创建ViewHolder实例，再将加载好的布局传入构造函数，最后返回ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repositorycard_component,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillCard skillCard =simple_skillCards.get(position);
        holder.name_ui.setText(skillCard.getName());
        holder.description_ui.setText(skillCard.getDescription());
        /*
        holder.num1_ui.setText(Integer.toString(skillCard.getNum1));
        holder.num2_ui.setText(Integer.toString(skillCard.getNum2));
        holder.num3_ui.setText(Integer.toString(skillCard.getNum3));
        holder.num4_ui.setText(Integer.toString(skillCard.getNum4));
        holder.num5_ui.setText(Integer.toString(skillCard.getNum5));
        holder.num6_ui.setText(Integer.toString(skillCard.getNum6));
        holder.num7_ui.setText(Integer.toString(skillCard.getNum7));
        holder.num8_ui.setText(Integer.toString(skillCard.getNum8));
         */
    }

    @Override
    public int getItemCount() {
            return simple_skillCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
    TextInputEditText name_ui ;
    TextInputEditText  description_ui;
    TextInputEditText num1_ui;
    TextInputEditText num2_ui;
    TextInputEditText num3_ui;
    TextInputEditText num4_ui;
    TextInputEditText num5_ui;
    TextInputEditText num6_ui;
    TextInputEditText num7_ui;
    TextInputEditText num8_ui;

       public ViewHolder(@NonNull View itemView) {
         super(itemView);
           name_ui = itemView.findViewById(R.id.name);
           description_ui = itemView.findViewById(R.id.description);
           num1_ui= itemView.findViewById(R.id.num1);
           num2_ui= itemView.findViewById(R.id.num2);
           num3_ui= itemView.findViewById(R.id.num3);
           num4_ui= itemView.findViewById(R.id.num4);
           num5_ui= itemView.findViewById(R.id.num5);
           num6_ui= itemView.findViewById(R.id.num6);
           num7_ui= itemView.findViewById(R.id.num7);
           num8_ui= itemView.findViewById(R.id.num8);
       }
    }
}
