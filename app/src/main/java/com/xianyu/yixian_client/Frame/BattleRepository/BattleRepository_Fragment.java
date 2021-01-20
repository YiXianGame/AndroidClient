package com.xianyu.yixian_client.Frame.BattleRepository;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.GroupAdapter;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.SkillCardAdapt;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattleRepositoryFragmentBinding;

import java.util.ArrayList;

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
        ExpandableListView expandableListView = binding.getRoot().findViewById(R.id.group_layout);
        expandableListView.setAdapter(new GroupAdapter(new ArrayList<>()));
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.skillcards_recycle);
        recyclerView.setAdapter(new SkillCardAdapt(new ArrayList<>()));
        viewModel.refreshAllSkillCards();
        viewModel.refreshUser();
        //初始化
        init();
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    private void init() {
        viewModel.cardGroups_live.observe(requireActivity(), lists -> {
            ExpandableListView expandableListView = binding.getRoot().findViewById(R.id.group_layout);
            expandableListView.setAdapter(new GroupAdapter(lists));
        });
        viewModel.skillcards_live.observe(requireActivity(), skillCards -> {
            RecyclerView recyclerView = binding.getRoot().findViewById(R.id.skillcards_recycle);
            SkillCardAdapt cardAdapt = (SkillCardAdapt) recyclerView.getAdapter();
            cardAdapt.refresh(skillCards);
            TextInputEditText editText = binding.getRoot().findViewById(R.id.searchName_textInput);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    cardAdapt.bluePrint.setName(s.toString());
                    cardAdapt.filter();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            Chip physics_chip = binding.getRoot().findViewById(R.id.physics_chip);
            physics_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setPhysics(isChecked);});
            Chip magic_chip = binding.getRoot().findViewById(R.id.magic_chip);
            magic_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setMagic(isChecked);});
            Chip cure_chip = binding.getRoot().findViewById(R.id.cure_chip);
            cure_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setCure(isChecked);});
            Chip attack_chip = binding.getRoot().findViewById(R.id.attack_chip);
            attack_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setAttack(isChecked);});
            Chip eternal_chip = binding.getRoot().findViewById(R.id.eternal_chip);
            eternal_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setEternal(isChecked);});
        });
    }
}
