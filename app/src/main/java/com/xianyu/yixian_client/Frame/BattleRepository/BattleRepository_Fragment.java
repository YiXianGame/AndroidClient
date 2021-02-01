package com.xianyu.yixian_client.Frame.BattleRepository;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.GroupAdapter;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirst;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionSecondNode;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.SkillCardAdapt;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattleRepositoryFragmentBinding;
import com.xianyu.yixian_client.databinding.BattleRepositoryHeadBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BattleRepository_Fragment extends Fragment {
    @Inject
    Repository repository;
    BattleRepositoryFragmentBinding binding;
    BattleRepository_ViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = BattleRepositoryFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(BattleRepository_ViewModel.class);
        viewModel.initialization(repository);
        //初始化
        init();
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    private void init() {
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.skillcards_recycle);
        SkillCardAdapt skillCardAdapt = new SkillCardAdapt();
        skillCardAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        skillCardAdapt.setAnimationFirstOnly(false);
        View head = BattleRepositoryHeadBinding.inflate(getLayoutInflater()).getRoot();
        skillCardAdapt.addHeaderView(head);
        BaseLoadMoreModule loadMoreModule = skillCardAdapt.getLoadMoreModule();
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setPreLoadNumber(10);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        recyclerView.setAdapter(skillCardAdapt);
        loadMoreModule.setOnLoadMoreListener(() -> {
            if(skillCardAdapt.skillCards_filters == null)loadMoreModule.loadMoreFail();
            int last_index = skillCardAdapt.getData().size()-1;
            if(last_index + 4 <= skillCardAdapt.skillCards_filters.size()){
                skillCardAdapt.addData(new ArrayList<>(skillCardAdapt.skillCards_filters.subList(last_index + 1,last_index + 4)));
                loadMoreModule.loadMoreComplete();
            }
            else if(last_index + 1 < skillCardAdapt.skillCards_filters.size()){
                skillCardAdapt.addData(new ArrayList<>(skillCardAdapt.skillCards_filters.subList(last_index + 1,skillCardAdapt.skillCards_filters.size())));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });
        viewModel.skillcards_live.observe(requireActivity(), skillCards -> {
            viewModel.skillcards_live.observe(getViewLifecycleOwner(), skillCardAdapt::filter);
        });

        TextInputEditText editText = head.findViewById(R.id.searchName_textInput);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillCardAdapt.bluePrint.setName(s.toString());
                skillCardAdapt.filter(viewModel.skillcards_live.getValue());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Chip physics_chip = head.findViewById(R.id.physics_chip);
        physics_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setPhysics(isChecked);
            skillCardAdapt.filter(viewModel.skillcards_live.getValue());});

        Chip magic_chip = head.findViewById(R.id.magic_chip);
        magic_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setMagic(isChecked);
            skillCardAdapt.filter(viewModel.skillcards_live.getValue());});

        Chip cure_chip = head.findViewById(R.id.cure_chip);
        cure_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setCure(isChecked);
            skillCardAdapt.filter(viewModel.skillcards_live.getValue());});

        Chip attack_chip = head.findViewById(R.id.attack_chip);
        attack_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setAttack(isChecked);
            skillCardAdapt.filter(viewModel.skillcards_live.getValue());});

        Chip eternal_chip = head.findViewById(R.id.eternal_chip);
        eternal_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setEternal(isChecked);
            skillCardAdapt.filter(viewModel.skillcards_live.getValue());});

        viewModel.refreshSkillCards();

        RecyclerView group_recycle = binding.getRoot().findViewById(R.id.group_layout);
        GroupAdapter groupAdapter = new GroupAdapter();
        groupAdapter.setAnimationFirstOnly(false);
        groupAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        group_recycle.setAdapter(groupAdapter);
        Core.liveUser.observe(requireActivity(), owner -> {
            List<BaseNode> roots = new ArrayList<>();
            for(CardGroup item : owner.getCardGroups()){
                roots.add(new CardGroupSectionFirstNode(item));
            }
            groupAdapter.setDiffNewData(roots);
        });
        groupAdapter.setOnItemClickListener((group_adapt, view, position) -> {
            ((GroupAdapter)(group_adapt)).expandOrCollapse(position);
        });
        BaseDraggableModule groupDraggableModule = groupAdapter.getDraggableModule();
        groupDraggableModule.setDragEnabled(true);
        groupDraggableModule.setSwipeEnabled(true);
        groupDraggableModule.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });
        groupDraggableModule.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                if(viewHolder.getItemViewType() == 0){
                    CardGroupSectionFirstNode node = (CardGroupSectionFirstNode)groupAdapter.getData().get(pos);
                    Core.liveUser.getValue().getCardGroups().remove(node.getCardGroup());
                    viewModel.updateUser(Core.liveUser.getValue());
                    return;
                }
                else if(viewHolder.getItemViewType() == 1){
                    CardGroupSectionFirstNode parent = (CardGroupSectionFirstNode)groupAdapter.getData().get(groupAdapter.findParentNode(pos));
                    CardGroupSectionSecondNode node = (CardGroupSectionSecondNode)groupAdapter.getData().get(pos);
                    parent.getCardGroup().getCards().remove(node.getSkillcard());
                    //插件bug,暂时不用这个,等修好再用
//                    try {
//
//                    }
//                    catch (Exception e){
//
//                    }
                    viewModel.updateUser(Core.liveUser.getValue());
                    return;
                }
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Core.liveUser.postValue(Core.liveUser.getValue());
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        viewModel.refreshUser();
    }
}
