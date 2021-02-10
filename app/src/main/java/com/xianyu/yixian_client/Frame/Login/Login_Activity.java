package com.xianyu.yixian_client.Frame.Login;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.LoginActivityBinding;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Login_Activity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    LoginViewModel viewModel;
    LoginActivityBinding binding;
    @Inject
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.initialization(repository);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        videoView = binding.getRoot().findViewById(R.id.back_ground);
        videoView.setVideoPath(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.cg_bg).toString());
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        mediaPlayer = MediaPlayer.create(this, R.raw.b);
        mediaPlayer.setLooping(true);
        ArrayList<SkillCard> skillCards = new ArrayList<SkillCard>(){};
        skillCards.add(new SkillCard());
        skillCards.add(new SkillCard());
        viewModel.repository.skillCardRepository.Test(skillCards);

        init();
    }

    private void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        videoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
        videoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        videoView.stopPlayback();
    }
}