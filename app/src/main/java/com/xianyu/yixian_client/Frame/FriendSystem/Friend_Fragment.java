package com.xianyu.yixian_client.Frame.FriendSystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.FriendSystem.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.FriendsActivityBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@AndroidEntryPoint
public class Friend_Fragment extends Fragment {
    FriendsActivityBinding binding;
    Friend_ViewModel viewModel;
    @Inject
    Repository repository;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = FriendsActivityBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(Friend_ViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }

    void init(){
        viewModel.queryFriendUsers(12345).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(users -> {
                    Friend_Adapt adapt = new Friend_Adapt(users);
                    RecyclerView recyclerView = binding.getRoot().findViewById(R.id.friends);
                    recyclerView.setAdapter(adapt);
                    TextInputEditText textInputEditText = binding.getRoot().findViewById(R.id.search_textInput);
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
                    CheckBox checkBox = binding.getRoot().findViewById(R.id.levelSort_check);
                    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setLevel(isChecked));
                    checkBox = binding.getRoot().findViewById(R.id.activeSort_check);
                    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setActive(isChecked));
                    checkBox = binding.getRoot().findViewById(R.id.reverseSort_check);
                    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> adapt.bluePrint.setReverse(isChecked));
                });
    }
}