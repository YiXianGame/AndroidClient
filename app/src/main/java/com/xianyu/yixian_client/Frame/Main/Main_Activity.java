package com.xianyu.yixian_client.Frame.Main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.hilt.lifecycle.ViewModelFactoryModules;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainActivityBinding;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@AndroidEntryPoint
public class Main_Activity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    @Inject
    Repository repository;
    MainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //视图绑定
        binding = MainActivityBinding.inflate(getLayoutInflater());
        MainViewModel viewModel =  new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initialization(repository);
        setContentView(binding.getRoot());
        videoView = binding.getRoot().findViewById(R.id.videoView_main);
        videoView.setVideoPath(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.cg_bg).toString());
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        mediaPlayer = MediaPlayer.create(this, R.raw.b);
        mediaPlayer.setLooping(true);
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
