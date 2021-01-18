package com.xianyu.yixian_client.Frame.Login.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.Login.LoginViewModel;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ForgetFragmentBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

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
@AndroidEntryPoint
public class Forget_Fragment extends Fragment {
    @Inject
    public LoginViewModel viewModel;
    public ForgetFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ForgetFragmentBinding.inflate(inflater,container,false);
        TextInputEditText userName_UI = binding.getRoot().findViewById(R.id.username);
        TextInputEditText passWord_UI = binding.getRoot().findViewById(R.id.password);
        TextInputEditText verification_UI = binding.getRoot().findViewById(R.id.verification_code);
        Core.liveUser.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                String ui_userName = userName_UI.getText().toString();
                String ui_password = passWord_UI.getText().toString();
                String ui_verificationCode = verification_UI.getText().toString();
                if(!ui_userName.equals(user.getUserName())){
                    userName_UI.setText(user.getUserName());
                }
                if(!ui_password.equals(user.getPasswords())){
                    passWord_UI.setText(user.getPasswords());
                }
                if(!ui_verificationCode.equals(viewModel.verificationCode.getValue())){
                    verification_UI.setText(viewModel.verificationCode.getValue());
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
                if(!s.toString().equals(Core.liveUser.getValue().getUserName())){
                    Core.liveUser.getValue().setUserName(s.toString());
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
                if(!s.toString().equals(Core.liveUser.getValue().getUserName())){
                    Core.liveUser.getValue().setPasswords(s.toString());
                    Core.liveUser.postValue(Core.liveUser.getValue());
                }
            }
        });
        verification_UI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(viewModel.verificationCode.getValue())){
                    viewModel.verificationCode.postValue(s.toString());
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
