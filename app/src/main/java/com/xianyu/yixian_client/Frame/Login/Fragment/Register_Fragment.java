package com.xianyu.yixian_client.Frame.Login.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.Login.LoginViewModel;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.LoginRegisterFragmentBinding;

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
public class Register_Fragment extends Fragment  {
    public LoginViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginRegisterFragmentBinding binding = LoginRegisterFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        TextInputEditText userName_UI = binding.getRoot().findViewById(R.id.username);
        TextInputEditText passWord_UI = binding.getRoot().findViewById(R.id.password);
        TextInputEditText surePassword_UI = binding.getRoot().findViewById(R.id.sure_password);
        Core.liveUser.observe(getViewLifecycleOwner(), user -> {
            String ui_userName = userName_UI.getText().toString();
            if(!ui_userName.equals(user.getUsername())){
                userName_UI.setText(user.getUsername());
            }
        });
        viewModel.password.observe(getViewLifecycleOwner(),s -> {
            if(!s.equals(passWord_UI.getText().toString()))passWord_UI.setText(s);
        });
        viewModel.surePassword.observe(getViewLifecycleOwner(),s -> {
            if(!s.equals(surePassword_UI.getText().toString()))surePassword_UI.setText(s);
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
                if(!s.toString().equals(Core.liveUser.getValue().getUsername())){
                    viewModel.password.postValue(s.toString());
                }
            }
        });
        surePassword_UI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(viewModel.surePassword.getValue())){
                    viewModel.surePassword.postValue(s.toString());
                }
            }
        });
        return binding.getRoot();
    }
    public Register_Fragment(){

    }
    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
