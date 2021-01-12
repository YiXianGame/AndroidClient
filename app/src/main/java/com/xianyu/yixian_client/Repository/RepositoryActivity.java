package com.xianyu.yixian_client.Repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.xianyu.yixian_client.databinding.RepositoryActivityBinding;


public class RepositoryActivity extends AppCompatActivity {
    RepositoryActivityBinding binding;
    RepositoryViewModel repositoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RepositoryActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //初始化
        Init();
    }

    private void Init() {
        //repositoryViewModel = new RepositoryViewModel();
        /*
        List<Simple_SkillCard> cards = new ArrayList();
        cards.add(new Simple_SkillCard("气仙盾","盾出挡势功",1,2,3,5,6,5,4,5));
        cards.add(new Simple_SkillCard("气斩","奋力一斩,是剑术中的一种绝对势功",4,2,4,2,0,4,4,5));
        cards.add(new Simple_SkillCard("气羽化","羽化而登仙，仙者不可灭",7,3,3,6,0,5,7,3));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        CardAdapt cardAdapt = new CardAdapt(cards);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cardAdapt);
         */
    }
}