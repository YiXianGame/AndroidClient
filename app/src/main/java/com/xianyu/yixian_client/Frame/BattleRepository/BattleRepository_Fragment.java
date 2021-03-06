package com.xianyu.yixian_client.Frame.BattleRepository;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirst;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Section.CardGroupSectionSecondNode;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.SkillCard_Adapt;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.Group_Adapt;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.CardGroup;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattleRepositoryFragmentBinding;
import com.xianyu.yixian_client.databinding.BattleRepositoryHeadBinding;
import com.yixian.material.Entity.SkillCard;

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
    CardGroupSectionFirstNode section;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = BattleRepositoryFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(BattleRepository_ViewModel.class);
        viewModel.initialization(repository);
        //?????????
        init();
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    private void init() {

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.skillcards_recycle);
        SkillCard_Adapt skillCardAdapt = new SkillCard_Adapt();
        skillCardAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        skillCardAdapt.setAnimationFirstOnly(false);
        View head = BattleRepositoryHeadBinding.inflate(getLayoutInflater()).getRoot();
        skillCardAdapt.setHeaderView(head);
        BaseLoadMoreModule loadMoreModule = skillCardAdapt.getLoadMoreModule();
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setPreLoadNumber(10);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        recyclerView.setAdapter(skillCardAdapt);
        loadMoreModule.setOnLoadMoreListener(() -> {
            if(skillCardAdapt.skillCards_filterBases == null)loadMoreModule.loadMoreFail();
            int last_index = skillCardAdapt.getData().size()-1;
            if(last_index + 4 <= skillCardAdapt.skillCards_filterBases.size()){
                skillCardAdapt.addData(new ArrayList<>(skillCardAdapt.skillCards_filterBases.subList(last_index + 1,last_index + 4)));
                loadMoreModule.loadMoreComplete();
            }
            else if(last_index + 1 < skillCardAdapt.skillCards_filterBases.size()){
                skillCardAdapt.addData(new ArrayList<>(skillCardAdapt.skillCards_filterBases.subList(last_index + 1,skillCardAdapt.skillCards_filterBases.size())));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });

        Core.liveSkillcards.observe(requireActivity(), skillCards -> {
            skillCardAdapt.filter(new ArrayList<>(skillCards.values()));
        });

        TextInputEditText editText = head.findViewById(R.id.searchName_textInput);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillCardAdapt.bluePrint.setName(s.toString());
                skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Chip physics_chip = head.findViewById(R.id.physics_chip);
        physics_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setPhysics(isChecked);
            skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));});

        Chip magic_chip = head.findViewById(R.id.magic_chip);
        magic_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setMagic(isChecked);
            skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));});

        Chip cure_chip = head.findViewById(R.id.cure_chip);
        cure_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setCure(isChecked);
            skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));});

        Chip attack_chip = head.findViewById(R.id.attack_chip);
        attack_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setAttack(isChecked);
            skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));});

        Chip eternal_chip = head.findViewById(R.id.eternal_chip);
        eternal_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            skillCardAdapt.bluePrint.setEternal(isChecked);
            skillCardAdapt.filter(new ArrayList<>(Core.liveSkillcards.getValue().values()));});

        RecyclerView group_recycle = binding.getRoot().findViewById(R.id.cardGroups_recycler);
        Group_Adapt groupAdapt = new Group_Adapt();
        groupAdapt.setAnimationFirstOnly(false);
        groupAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        group_recycle.setAdapter(groupAdapt);
        Core.liveUser.observe(requireActivity(), owner -> {
            List<BaseNode> roots = new ArrayList<>();
            for(CardGroup item : owner.getCardGroups()){
                roots.add(new CardGroupSectionFirstNode(item));
            }
            groupAdapt.setDiffNewData(roots);
        });
        groupAdapt.setOnItemClickListener((group_adapt, view, position) -> {
            ((Group_Adapt)(group_adapt)).expandOrCollapse(position);
        });
        BaseDraggableModule groupDraggableModule = groupAdapt.getDraggableModule();
        groupDraggableModule.setDragEnabled(true);
        groupDraggableModule.setSwipeEnabled(true);
        groupDraggableModule.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                if(viewHolder.getItemViewType() == 1){
                    CardGroupSectionFirstNode parent = (CardGroupSectionFirstNode) groupAdapt.getData().get(groupAdapt.findParentNode(pos));
                    CardGroupSectionSecondNode node = (CardGroupSectionSecondNode) groupAdapt.getData().get(pos);
                    parent.getChildNode().remove(node);
                    parent.getCardGroup().getCards().remove(node.getSkillcard().getId());
                }
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                if(viewHolder.getItemViewType() == 0){
                    Core.liveUser.getValue().getCardGroups().clear();
                    for (BaseNode baseNode : groupAdapt.getData()){
                        if(baseNode instanceof CardGroupSectionFirstNode){
                            Core.liveUser.getValue().getCardGroups().add(((CardGroupSectionFirstNode) baseNode).getCardGroup());
                        }
                    }
                    viewModel.updateCardGroups(Core.liveUser.getValue());
                    Core.liveUser.postValue(Core.liveUser.getValue());
                }
                if(viewHolder.getItemViewType() == 1){
                    CardGroupSectionFirstNode parent = null;
                    for(int i=pos;i>=0;i--){
                        if(groupAdapt.getItemViewType(i) == 0){
                            parent = (CardGroupSectionFirstNode) groupAdapt.getItem(i);
                            break;
                        }
                    }
                    if(parent != null){
                        CardGroupSectionSecondNode node = (CardGroupSectionSecondNode) groupAdapt.getItem(pos);
                        parent.getCardGroup().getCards().add(node.getSkillcard().getId());
                        parent.getChildNode().add(node);
                        viewModel.updateCardGroups(Core.liveUser.getValue());
                    }
                }
            }
        });
        groupDraggableModule.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                if(viewHolder.getItemViewType() == 0){
                    CardGroupSectionFirstNode node = (CardGroupSectionFirstNode) groupAdapt.getData().get(pos);
                    groupAdapt.removeAt(pos);
                    Core.liveUser.getValue().getCardGroups().remove(node.getCardGroup());
                }
                else if(viewHolder.getItemViewType() == 1){
                    CardGroupSectionFirstNode parent = (CardGroupSectionFirstNode) groupAdapt.getData().get(groupAdapt.findParentNode(pos));
                    CardGroupSectionSecondNode node = (CardGroupSectionSecondNode) groupAdapt.getData().get(pos);
                    parent.getChildNode().remove(node);
                    parent.getCardGroup().getCards().remove(node.getSkillcard().getId());
                }
                viewModel.updateCardGroups(Core.liveUser.getValue());
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        //??????????????????
        groupAdapt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(adapter.getItemViewType(position) == 0){
                    section = (CardGroupSectionFirstNode) adapter.getItem(position);
                }
            }
        });
        groupAdapt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ((Group_Adapt)adapter).expandOrCollapse(position);
            }
        });
        //?????????????????????????????????????????????
        skillCardAdapt.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if(section == null){
                    if(Core.liveUser.getValue().getCardGroups() == null)Core.liveUser.getValue().setCardGroups(new ArrayList<>());
                    CardGroup cardGroup = new CardGroup();
                    cardGroup.setName("??????");
                    SkillCard card = (SkillCard) adapter.getData().get(position);
                    cardGroup.getCards().add(card.getId());
                    Core.liveUser.getValue().getCardGroups().add(cardGroup);
                    Core.liveUser.postValue(Core.liveUser.getValue());
                }
                else {
                    SkillCard card = (SkillCard) adapter.getData().get(position);
                    groupAdapt.nodeAddData(section,new CardGroupSectionSecondNode(Core.liveSkillcards.getValue().get(card.getId())));
                    section.getCardGroup().getCards().add(card.getId());
                }
                viewModel.updateCardGroups(Core.liveUser.getValue());
                return true;
            }
        });
    }
}
