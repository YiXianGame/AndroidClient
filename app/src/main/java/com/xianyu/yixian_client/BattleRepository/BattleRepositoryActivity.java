package com.xianyu.yixian_client.BattleRepository;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.BattlerepositoryActivityBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
        Init();
    }

    private void Init() {
        Log.d(Tag.Debug,"ViewModel的信息是"+viewModel.d);
        List<SkillCard> cards = new ArrayList();
        SkillCard skillCard = new SkillCard();
        skillCard.setName("技能卡1");
        skillCard.setMp(10);
        cards.add(skillCard);
        skillCard.setName("技能卡2");
        skillCard.setMp(20);
        cards.add(skillCard);
        CardAdapt cardAdapt = new CardAdapt(cards);
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recycler_view);
        recyclerView.setAdapter(cardAdapt);
    }

}
