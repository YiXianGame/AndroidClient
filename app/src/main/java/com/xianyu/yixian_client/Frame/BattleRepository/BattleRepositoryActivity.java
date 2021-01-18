package com.xianyu.yixian_client.Frame.BattleRepository;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.GroupAdapter;
import com.xianyu.yixian_client.Frame.BattleRepository.Adapt.SkillCardAdapt;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattlerepositoryActivityBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@AndroidEntryPoint
public class BattleRepositoryActivity extends AppCompatActivity {
    BattlerepositoryActivityBinding binding;
    @Inject
    BattleRepositoryViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BattlerepositoryActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //初始化
        init();
    }

    @SuppressLint("CheckResult")
    private void init() {
        ExpandableListView expandableListView;
        expandableListView = binding.getRoot().findViewById(R.id.group_layout);
        viewModel.repository.queryUserById(123456)
                .subscribeOn(Schedulers.io())//查询数据时的线程
                .observeOn(AndroidSchedulers.mainThread())//数据查找完毕的线程
                .subscribe(users -> {
                    GroupAdapter groupAdapter = new GroupAdapter(users.getCardGroups());
                    expandableListView.setAdapter(groupAdapter);
                    for (CardGroup cardGroup : users.getCardGroups()){
                        for(Long id : cardGroup.getCards_id()){
                            viewModel.repository.querySkillCardById(id)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(skillCard ->{ cardGroup.getCards().add(skillCard); groupAdapter.notifyDataSetChanged();});
                        }
                    }
                });
        viewModel.repository.queryAllSkillCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(skillCards -> {
                    Core.liveSkillcards.setValue(new ArrayList<>(skillCards));
                    RecyclerView recyclerView;
                    SkillCardAdapt cardAdapt = new SkillCardAdapt(Core.liveSkillcards.getValue());
                    recyclerView = binding.getRoot().findViewById(R.id.recycler_view);
                    recyclerView.setAdapter(cardAdapt);
                    TextInputEditText editText = findViewById(R.id.searchName_textInput);
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
                    Chip physics_chip = findViewById(R.id.physics_chip);
                    physics_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setPhysics(isChecked);cardAdapt.filter();});
                    Chip magic_chip = findViewById(R.id.magic_chip);
                    magic_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setMagic(isChecked);cardAdapt.filter();});
                    Chip cure_chip = findViewById(R.id.cure_chip);
                    cure_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setCure(isChecked);cardAdapt.filter();});
                    Chip attack_chip = findViewById(R.id.attack_chip);
                    attack_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setAttack(isChecked);cardAdapt.filter();});
                    Chip eternal_chip = findViewById(R.id.eternal_chip);
                    eternal_chip.setOnCheckedChangeListener((buttonView, isChecked) -> {cardAdapt.bluePrint.setEternal(isChecked);cardAdapt.filter();});
                });
    }
}
