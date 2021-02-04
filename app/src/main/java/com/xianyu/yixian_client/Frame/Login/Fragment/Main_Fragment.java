package com.xianyu.yixian_client.Frame.Login.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.Login_Fragment_Adapter;
import com.xianyu.yixian_client.Frame.Login.LoginViewModel;
import com.xianyu.yixian_client.Utils.ShortCode.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.Utils.MD5Utils;
import com.xianyu.yixian_client.databinding.LoginMainFragmentBinding;

public class Main_Fragment extends Fragment {
    private LoginMainFragmentBinding binding;
    private ViewPager2 paper;
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
        //UI数据初始化
        viewModel.password.postValue("");
        viewModel.surePassword.postValue("");
        viewModel.verificationCode.postValue("");
        //跳出主线程
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        //fragment绑定初始化
        paper = binding.getRoot().findViewById(R.id.paper);
        paper.setPageTransformer(new DepthPageTransformer());
        paper.setAdapter(new Login_Fragment_Adapter(requireActivity()));
        TabLayout tab = binding.getRoot().findViewById(R.id.tabLayout);
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
                    case 0: Register_Click();break;
                    case 1:Login_Click(); break;
                    case 2:Forget_Click(); break;
                    default:break;
                }
            }
        });
        new TabLayoutMediator(
                tab,
                paper,
                (item, position) -> {
                    switch (position){
                        case 0 : item.setText(R.string.register);break;
                        case 1 : item.setText(R.string.login);break;
                        case 2 : item.setText(R.string.pw_forget);break;
                        default: break;
                    }
                }
        ).attach();
        paper.setOffscreenPageLimit(3);
        tab.selectTab(tab.getTabAt(1));
    }

    public void Login_Click() {
        if(Core.liveUser == null || viewModel.password.getValue().isEmpty() || Core.liveUser.getValue().getUsername().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"登录失败","内容不能为空");
        }
        else {
            viewModel.repository.loginUser(Core.liveUser.getValue(), MD5Utils.encrypt(viewModel.password.getValue())).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(result->{
                if(result == -1){
                    MessageDialog.Error_Dialog(getContext(),"登录失败","该账户尚未注册");
                }
                else if(result == -2){
                    MessageDialog.Error_Dialog(getContext(),"登录失败","账户或密码错误");
                }
                else {
                    viewModel.init_User(Core.liveUser);
                    Core.liveUser.getValue().setPassword(viewModel.password.getValue());
                    //viewModel.repository.insertUser(Core.liveUser.getValue());
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle("登陆成功")
                            .setMessage("欢迎您的回归！")
                            .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                                dialog.dismiss();
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_Activity_to_main_navigation);
                            })
                            .show();
                }
            });
        }
    }
    public void Register_Click()  {
        if(Core.liveUser == null || viewModel.password.getValue().isEmpty() || Core.liveUser.getValue().getUsername().isEmpty() || viewModel.surePassword.getValue().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"注册失败","内容不能为空");
        }
        else if(viewModel.surePassword.getValue().equals(viewModel.surePassword.getValue())){
            viewModel.repository.registerUser(Core.liveUser.getValue(),viewModel.password.getValue()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(value -> {
                if(value != -1 && value != -2){
                    MessageDialog.Error_Dialog(getContext(),"ID:value","恭喜您注册成功！" );
                }
                else MessageDialog.Error_Dialog(getContext(),"注册失败","重复密码与密码不一致");
            });
        }
        else MessageDialog.Error_Dialog(getContext(),"注册失败","重复密码与密码不一致");
    }
    public void Forget_Click() {
        if(Core.liveUser == null || viewModel.password.getValue().isEmpty() || Core.liveUser.getValue().getUsername().isEmpty() || viewModel.verificationCode.getValue().isEmpty()){
            MessageDialog.Error_Dialog(getContext(),"找回失败","内容不能为空");
        }
        else{

        }
    }
}
