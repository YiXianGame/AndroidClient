package com.xianyu.yixian_client.Frame.FriendSystem;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Frame.FriendSystem.Adapt.Friend_Adapt;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.FriendsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class Friend_Fragment extends Fragment {
    FriendsFragmentBinding binding;
    Friend_ViewModel viewModel;
    @Inject
    Repository repository;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = FriendsFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(Friend_ViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }

    void init(){
        //好友列表
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.friends_recycle);
        Friend_Adapt friend_adapt = new Friend_Adapt();
        BaseLoadMoreModule loadMoreModule = friend_adapt.getLoadMoreModule();
        friend_adapt.setAnimationEnable(true);
        friend_adapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        friend_adapt.setAnimationFirstOnly(false);
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(1);
        loadMoreModule.setOnLoadMoreListener(() -> {
            List<User> users = friend_adapt.friends_filters;
            if(users == null)loadMoreModule.loadMoreFail();
            int last_index = friend_adapt.getData().size() - 1;
            if(last_index + 1 < users.size()){
                friend_adapt.addData(new ArrayList<>(users.subList(last_index + 1,last_index + 2)));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });
        viewModel.friends_live.observe(getViewLifecycleOwner(), friend_adapt::filter);
        viewModel.refreshFriends(Core.liveUser.getValue().getId());
        CheckBox checkBox = binding.getRoot().findViewById(R.id.levelSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {friend_adapt.bluePrint.setLevel(isChecked);friend_adapt.filter(viewModel.friends_live.getValue());});
        checkBox = binding.getRoot().findViewById(R.id.activeSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {friend_adapt.bluePrint.setActive(isChecked);friend_adapt.filter(viewModel.friends_live.getValue());});
        TextInputEditText name_textInput = binding.getRoot().findViewById(R.id.search_textInput);
        name_textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                friend_adapt.bluePrint.setNickName(s.toString());
                friend_adapt.filter(viewModel.friends_live.getValue());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recyclerView.setAdapter(friend_adapt);
        viewModel.refreshFriends(Core.liveUser.getValue().getId());
    }
}