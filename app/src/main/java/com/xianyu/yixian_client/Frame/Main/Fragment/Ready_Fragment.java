package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Main.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Frame.Main.Adapt.ReadyInvite_Adapt;
import com.xianyu.yixian_client.Frame.Main.Adapt.ReadyUser_Adapt;
import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainChooseModeFragmentBinding;
import com.xianyu.yixian_client.databinding.MainReadyFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class Ready_Fragment extends Fragment {
    private MainReadyFragmentBinding binding;
    private MainViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainReadyFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        init();
        return binding.getRoot();
    }

    private void init() {
        //好友
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.invite_recycle);
        ReadyInvite_Adapt friend_adapt = new ReadyInvite_Adapt();
        BaseLoadMoreModule loadMoreModule = friend_adapt.getLoadMoreModule();
        friend_adapt.setAnimationEnable(true);
        friend_adapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
        friend_adapt.setAnimationFirstOnly(false);
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(1);
        loadMoreModule.setOnLoadMoreListener(() -> {
            List<User> users = friend_adapt.filter(viewModel.friends_live.getValue());
            if(users == null)loadMoreModule.loadMoreFail();
            int last_index = users.lastIndexOf(friend_adapt.getData().get(friend_adapt.getData().size() - 1));
            if(last_index + 1 < users.size()){
                friend_adapt.addData(new ArrayList<>(users.subList(last_index + 1,last_index + 2)));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });
        viewModel.friends_live.observe(getViewLifecycleOwner(), list -> {
            List<User> users = friend_adapt.filter(viewModel.friends_live.getValue());
            if (users != null){
                if(users.size() >= 9){
                    friend_adapt.setDiffNewData(users.subList(0,9));
                }
                else friend_adapt.setDiffNewData(users);
            }
            else friend_adapt.setDiffNewData(new ArrayList<>());
        });
        viewModel.refreshFriends(Core.liveUser.getValue().getId());
        recyclerView.setAdapter(friend_adapt);

        recyclerView = binding.getRoot().findViewById(R.id.pos_recycle);
        ReadyUser_Adapt readyUser_adapt= new ReadyUser_Adapt();
        readyUser_adapt.setDiffNewData(new ArrayList<>());
        Core.liveUser.observe(getViewLifecycleOwner(),user ->  {
            readyUser_adapt.getData().add(Core.liveUser.getValue());
            readyUser_adapt.setDiffNewData(readyUser_adapt.getData());
        });
        recyclerView.setAdapter(readyUser_adapt);
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
