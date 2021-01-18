package com.xianyu.yixian_client.Frame.FriendSystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.FriendSystem.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.FriendsActivityBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Friend_Activity extends AppCompatActivity {
    ArrayList<User> friends;
    FriendsActivityBinding binding;
    @Inject
    Friend_ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding初始化
        binding = FriendsActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(Friend_ViewModel.class);
        setContentView(binding.getRoot());
        init();
    }
    @SuppressLint("CheckResult")
    void init(){
        ArrayList<User> friends_data = new ArrayList<>();
        Friend_Adapt adapt = new Friend_Adapt(friends_data);
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.friends);
        recyclerView.setAdapter(adapt);
        TextInputEditText textInputEditText = findViewById(R.id.search_textInput);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapt.bluePrint.setNickName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CheckBox checkBox = findViewById(R.id.levelSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setLevel(isChecked));
        checkBox = findViewById(R.id.activeSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setActive(isChecked));
        checkBox = findViewById(R.id.reverseSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setReverse(isChecked));

        viewModel.repository.queryUserById(123456)
                .subscribeOn(Schedulers.io())//查询数据时的线程
                .observeOn(AndroidSchedulers.mainThread())//数据查找完毕的线程
                .subscribe(user -> {
                    Core.liveUser.setValue(user);
                    viewModel.repository.queryFriend(Core.liveUser.getValue().getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(friends -> {
                                for (Friend friend:friends) {
                                    long id;
                                    if (friend.getUser_1() != Core.liveUser.getValue().getId())id = friend.getUser_1();
                                    else id = friend.getUser_2();
                                    viewModel.repository.queryUserById(id)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(user_friend -> {
                                                friends_data.add(user_friend);
                                                adapt.refreshData(friends_data);
                                            });
                                }
                            });
                });
    }
}