package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.History.Adapt;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textview.MaterialTextView;
import com.yixian.material.Entity.History;
import com.xianyu.yixian_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class HistoryAdapt extends BaseQuickAdapter<History,HistoryAdapt.ViewHolder> {

    public HistoryAdapt() {
        super(R.layout.personal_profile_history_item);
        setDiffCallback(new DiffCallBack());
    }

    @Override
    protected void convert(@NotNull ViewHolder holder, History history) {
        if(history.isResult()){
            holder.result_text.setText(R.string.victory);
        }
        else holder.result_text.setText(R.string.defeat);
        holder.kills_text.setText(String.format(Locale.getDefault(),"%d", history.getKills()));
    }

    public class ViewHolder extends BaseViewHolder {
        MaterialTextView result_text;
        MaterialTextView kills_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result_text = itemView.findViewById(R.id.result_text);
            kills_text = itemView.findViewById(R.id.kills_text);
        }
    }
    protected class DiffCallBack extends DiffUtil.ItemCallback<History>{
        @Override
        public boolean areItemsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem.getTime() == newItem.getTime();
        }
        @Override
        public boolean areContentsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem.getTime() == newItem.getTime();
        }
    }
}
