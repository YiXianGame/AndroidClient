package com.xianyu.yixian_client.Frame.BattleRepository.Adapt;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.R;

import java.util.ArrayList;

public class GroupAdapter extends BaseExpandableListAdapter {
    private ArrayList<CardGroup> groups;
    public GroupAdapter(ArrayList<CardGroup> groups){
        this.groups = groups;
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getCards().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getCards().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getCards().get(childPosition).first;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.battle_repository_group_item,parent,false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }
        else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.name_text.setText(groups.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GroupChildrenViewHolder groupChildrenViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.battle_repository_groupshow_item,parent,false);
            groupChildrenViewHolder = new GroupChildrenViewHolder(convertView);
            convertView.setTag(groupChildrenViewHolder);
        }
        else {
            groupChildrenViewHolder = (GroupChildrenViewHolder)convertView.getTag();
        }
        groupChildrenViewHolder.name_text.setText(groups.get(groupPosition).getCards().get(childPosition).second);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public class GroupViewHolder{
        MaterialTextView name_text;
        public GroupViewHolder(@NonNull View itemView) {
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
    public class GroupChildrenViewHolder{
        MaterialTextView name_text;
        public GroupChildrenViewHolder(@NonNull View itemView) {
            name_text = itemView.findViewById(R.id.name_text);
        }
    }
}
