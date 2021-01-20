package com.xianyu.yixian_client.Frame.Login.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.Login_Fragment_Adapter;
import com.xianyu.yixian_client.Frame.Login.LoginViewModel;
import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.ShortCode.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.LoginMainFragmentBinding;
import com.xianyu.yixian_client.databinding.MainMainFragmentBinding;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Main_Fragment extends Fragment {
    private LoginMainFragmentBinding binding;
    private ViewPager2 paper;
    private TabLayout tab;
    LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(binding == null){
            binding = LoginMainFragmentBinding.inflate(inflater,container,false);
            viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
            init();
        }
        return binding.getRoot();
    }


    private void init(){
        //跳出主线程
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        //fragment绑定初始化
        paper = binding.getRoot().findViewById(R.id.paper);
        paper.setPageTransformer(new DepthPageTransformer());
        paper.setAdapter(new Login_Fragment_Adapter(requireActivity()));
        tab = binding.getRoot().findViewById(R.id.tabLayout);
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

    }

    public void Login_Click() {
        if(Core.liveUser == null || Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"登录失败","内容不能为空");
        }
        else {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_Activity_to_main_navigation);
            requireActivity().finish();
        }
    }

    public void Register_Click() {
        if(Core.liveUser == null || Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty() || viewModel.surePassword.getValue().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"注册失败","内容不能为空");
        }
        else if(Core.liveUser.getValue().getPasswords().equals(viewModel.surePassword.getValue())){

        }
        else MessageDialog.Error_Dialog(getContext(),"注册失败","重复密码与密码不一致");
    }
    public void Forget_Click() {
        if(Core.liveUser == null || Core.liveUser.getValue().getPasswords().isEmpty() || Core.liveUser.getValue().getUserName().isEmpty() || viewModel.verificationCode.getValue().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"找回失败","内容不能为空");
        }
        else if(!Core.liveUser.getValue().getPasswords().equals(viewModel.surePassword)){

        }
    }
}
