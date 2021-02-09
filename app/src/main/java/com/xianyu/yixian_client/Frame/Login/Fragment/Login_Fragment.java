package com.xianyu.yixian_client.Frame.Login.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.Login.LoginViewModel;
import com.xianyu.yixian_client.R;
import com.yixian.make.Core;
import com.yixian.material.Entity.Config;
import com.yixian.material.Entity.User;
import com.yixian.material.Utils.MD5Utils;
import com.xianyu.yixian_client.databinding.LoginLoginFragmentBinding;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Login
 * @ClassName: Login_Fragment
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/18 21:51
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/18 21:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Login_Fragment extends Fragment {

    public LoginViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginLoginFragmentBinding binding = LoginLoginFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        TextInputEditText userName_UI = binding.getRoot().findViewById(R.id.username);
        TextInputEditText passWord_UI = binding.getRoot().findViewById(R.id.password);
        Core.liveUser.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                String ui_userName = userName_UI.getText().toString();
                if(!ui_userName.equals(user.getUsername())){
                    userName_UI.setText(user.getUsername());
                }
            }
        });
        userName_UI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(Core.liveUser.getValue().getUsername())){
                    Core.liveUser.getValue().setUsername(s.toString());
                    Core.liveUser.postValue(Core.liveUser.getValue());
                }
            }
        });
        passWord_UI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!MD5Utils.encrypt(s.toString()).equals(Core.liveUser.getValue().getPassword())){
                    Core.liveUser.getValue().setPassword(MD5Utils.encrypt(s.toString()));
                }
            }
        });
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
