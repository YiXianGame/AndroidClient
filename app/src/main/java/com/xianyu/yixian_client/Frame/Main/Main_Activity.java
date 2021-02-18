package com.xianyu.yixian_client.Frame.Main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainActivityBinding;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Main_Activity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    @Inject
    Repository repository;
    MainActivityBinding binding;
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //视图绑定
        binding = MainActivityBinding.inflate(getLayoutInflater());
        viewModel =  new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initialization(repository);
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        videoView = binding.getRoot().findViewById(R.id.videoView_main);
        videoView.setVideoPath(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.cg_bg).toString());
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        mediaPlayer = MediaPlayer.create(this, R.raw.b);
        mediaPlayer.setLooping(true);
        //同步用户属性
        viewModel.syncUserAttribute(Core.liveUser);
        //同步技能卡
        viewModel.syncSkillCard(Core.liveUser);
        //同步好友
        viewModel.syncUserFriend(Core.liveUser);
        //同步好友
        viewModel.syncUserCardGroups(Core.liveUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mediaPlayer.start();
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
