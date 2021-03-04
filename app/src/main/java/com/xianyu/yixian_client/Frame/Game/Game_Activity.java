package com.xianyu.yixian_client.Frame.Game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.GameActivityBinding;
import com.yixian.make.Model.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Game_Activity extends AppCompatActivity {
    GameViewModel viewModel;
    GameActivityBinding binding;
    @Inject
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        viewModel.initialization(repository);
        binding = GameActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}