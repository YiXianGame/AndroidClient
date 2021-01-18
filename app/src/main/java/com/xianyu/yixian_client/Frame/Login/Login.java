package com.xianyu.yixian_client.Frame.Login;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.Login_Fragment_Adapter;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.ShortCode.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.LoginActivityBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Login extends AppCompatActivity {
    private ViewPager2 paper;
    private TabLayout tab;
    LoginViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivityBinding binding = LoginActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setContentView(binding.getRoot());
        //初始化
        Init();
    }
    private void Init(){
        //音频初始化
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.b);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        //视频初始化
        VideoView videoView = findViewById(R.id.back_ground);
        videoView.setVideoPath(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.cg_bg).toString());
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        videoView.start();
        //跳出主线程
        Disposable temp = Observable.create((ObservableOnSubscribe<LoginViewModel>) emitter -> {
            //自定义注入
            emitter.onNext(viewModel);
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(loginViewModel -> {
            viewModel = loginViewModel;
            //fragment绑定初始化
            paper = findViewById(R.id.paper);
            paper.setPageTransformer(new DepthPageTransformer());
            paper.setAdapter(new Login_Fragment_Adapter(this));
            tab = findViewById(R.id.tabLayout);
            tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    switch (tab.getPosition()){
                        case 0:Register_Click(); break;
                        case 1:Login_Click(); break;
                        case 2:Forget_Click(); break;
                        default:break;
                       }
                }
            });
            new TabLayoutMediator(
                    tab,
                    paper,
                    (tab, position) -> {
                        switch (position){
                            case 0 : tab.setText(R.string.register);break;
                            case 1 : tab.setText(R.string.login);break;
                            case 2 : tab.setText(R.string.pw_forget);break;
                            default: break;
                        }
                    }
            ).attach();
            paper.setOffscreenPageLimit(3);
            tab.selectTab(tab.getTabAt(1));
        });
    }
    @Override
    public void onBackPressed() {
        if (paper.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            paper.setCurrentItem(paper.getCurrentItem() - 1);
        }
    }

    public void Login_Click() {
        if(Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty()){
            MessageDialog.Error_Dialog(this,"登录失败","内容不能为空");
        }

    }

    public void Register_Click() {
        if(Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty() || viewModel.surePassword.getValue().isEmpty()){
            MessageDialog.Error_Dialog(this,"注册失败","内容不能为空");
        }
        else if(Core.liveUser.getValue().getPasswords().equals(viewModel.surePassword.getValue())){

        }
        else MessageDialog.Error_Dialog(this,"注册失败","重复密码与密码不一致");
    }
    public void Forget_Click() {
        if(Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty() || viewModel.verificationCode.getValue().isEmpty()){
            MessageDialog.Error_Dialog(this,"找回失败","内容不能为空");
        }
        else if(!Core.liveUser.getValue().getPasswords().equals(viewModel.surePassword)){

        }
    }
}