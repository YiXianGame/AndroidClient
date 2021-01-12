package com.xianyu.yixian_client.Main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Login.LoginService;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding binding;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //视窗绑定
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

    }

    private void init() {
        mainViewModel = new MainViewModel();
        Intent intentOne = new Intent(this, LoginService.class);
        startService(intentOne);

        TextView text1 = binding.getRoot().findViewById(R.id.textView_experience);
        TextView text2 = binding.getRoot().findViewById(R.id.textView_username);

        /*mainViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!text1.getText().toString().equals(s)){
                    text1.setText(s);
                }
                if(!text2.getText().toString().equals(s)){
                    text2.setText(s);
                }
            }
        });
        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainViewModel.message.postValue(s.toString());
                }
        });
        text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainViewModel.message.postValue(s.toString());
            }
        });*/
        text1.setText("我是复制体");
    }
}
