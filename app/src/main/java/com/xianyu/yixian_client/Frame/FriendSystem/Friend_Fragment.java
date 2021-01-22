package com.xianyu.yixian_client.Frame.FriendSystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.FriendSystem.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.User;
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
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(4);
        loadMoreModule.setOnLoadMoreListener(() -> {
            List<User> users = friend_adapt.filter(new ArrayList<>(viewModel.users_live.getValue()));
            if(users == null)loadMoreModule.loadMoreFail();
            int last_index = users.lastIndexOf(friend_adapt.getData().get(friend_adapt.getData().size() - 1));
            if(last_index + 4 <= users.size()){
                friend_adapt.addData(new ArrayList<>(users.subList(last_index + 1,last_index + 4)));
                loadMoreModule.loadMoreComplete();
            }
            else if(last_index + 1 != users.size()){
                friend_adapt.addData(new ArrayList<>(users.subList(last_index + 1,users.size())));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });
        viewModel.users_live.observe(getViewLifecycleOwner(), list -> {
            List<User> users = friend_adapt.filter(new ArrayList<>(new ArrayList<>(viewModel.users_live.getValue())));
            if (users != null){
                if(users.size() >= 9){
                    friend_adapt.setDiffNewData(friend_adapt.filter(users.subList(0,9)));
                }
                else friend_adapt.setDiffNewData(friend_adapt.filter(users));
            }
            else friend_adapt.setDiffNewData(new ArrayList<>());
        });
        viewModel.refreshSkillCards(Core.liveUser.getValue().getId());
        CheckBox checkBox = binding.getRoot().findViewById(R.id.levelSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {friend_adapt.bluePrint.setLevel(isChecked);friend_adapt.setDiffNewData(friend_adapt.filter(new ArrayList<>(viewModel.users_live.getValue())));});
        checkBox = binding.getRoot().findViewById(R.id.activeSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {friend_adapt.bluePrint.setActive(isChecked);friend_adapt.setDiffNewData(friend_adapt.filter(new ArrayList<>(viewModel.users_live.getValue())));});
        checkBox = binding.getRoot().findViewById(R.id.reverseSort_check);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {friend_adapt.bluePrint.setReverse(isChecked);friend_adapt.setDiffNewData(friend_adapt.filter(new ArrayList<>(viewModel.users_live.getValue())));});
    }
}