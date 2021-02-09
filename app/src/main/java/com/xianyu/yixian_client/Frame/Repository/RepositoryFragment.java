package com.xianyu.yixian_client.Frame.Repository;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.Repository.Adapt.CardAdapt;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattleRepositoryHeadBinding;
import com.xianyu.yixian_client.databinding.RepositoryFragmentBinding;
import com.yixian.make.Model.Repository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RepositoryFragment extends Fragment {
    @Inject
    Repository repository;
    RepositoryViewModel viewModel;
    RepositoryFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = RepositoryFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(RepositoryViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }

    private void init() {
        CardAdapt skillCardAdapt = new CardAdapt();
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.card_frame);
        skillCardAdapt.setAnimationFirstOnly(false);
        skillCardAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
        View head = BattleRepositoryHeadBinding.inflate(getLayoutInflater()).getRoot();
        skillCardAdapt.addHeaderView(head);
        BaseLoadMoreModule loadMoreModule = skillCardAdapt.getLoadMoreModule();
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setPreLoadNumber(10);
        loadMoreModule.setEnableLoadMoreEndClick(false);
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

        recyclerView.setAdapter(skillCardAdapt);
        viewModel.refreshSkillCards();

    }
}