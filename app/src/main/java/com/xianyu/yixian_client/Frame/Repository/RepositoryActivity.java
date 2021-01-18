package com.xianyu.yixian_client.Frame.Repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.RepositoryActivityBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class RepositoryActivity extends AppCompatActivity {
    RepositoryViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RepositoryActivityBinding binding = RepositoryActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(RepositoryViewModel.class);
        setContentView(binding.getRoot());
        //初始化
        Init();
    }

    private void Init() {
        CardAdapt cardAdapt = new CardAdapt(viewModel.getCards());
        RecyclerView recyclerView = findViewById(R.id.card_frame);
        recyclerView.setAdapter(cardAdapt);
    }
}