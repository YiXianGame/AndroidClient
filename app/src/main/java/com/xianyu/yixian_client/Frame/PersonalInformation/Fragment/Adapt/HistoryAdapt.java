package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.Adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class HistoryAdapt extends BaseQuickAdapter<History,HistoryAdapt.ViewHolder> {


    public HistoryAdapt() {
        super(R.layout.personal_profile_history_item);
    }

    @Override
    protected void convert(@NotNull ViewHolder holder, History history) {
        if(history.isResult()){
            holder.result_text.setText(R.string.victory);
        }
        else holder.result_text.setText(R.string.defeat);
        holder.kills_text.setText(String.format(Locale.getDefault(),"%d",history.getKills()));
    }

    public static class ViewHolder extends BaseViewHolder {
        MaterialTextView result_text;
        MaterialTextView kills_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result_text = itemView.findViewById(R.id.result_text);
            kills_text = itemView.findViewById(R.id.kills_text);
        }
    }
}
