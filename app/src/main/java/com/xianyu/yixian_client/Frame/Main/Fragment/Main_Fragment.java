package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.tabs.TabLayout;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Main.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainMainFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class Main_Fragment extends Fragment {
    private MainMainFragmentBinding binding;
    private MainViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainMainFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        init();
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
    private void init() {
        Button round_button = binding.getRoot().findViewById(R.id.round_button);
        Button realTime_button = binding.getRoot().findViewById(R.id.realTime_button);
        round_button.setOnClickListener(v -> {
            Main_FragmentDirections.ActionMainDestToChooseModeDest action = Main_FragmentDirections.actionMainDestToChooseModeDest();
            action.setRoomMode(Enums.Room_Type.Battle_Royale);
            Navigation.findNavController(v).navigate(action);
        });
        realTime_button.setOnClickListener(v -> {
            Main_FragmentDirections.ActionMainDestToChooseModeDest action = Main_FragmentDirections.actionMainDestToChooseModeDest();
            action.setRoomMode(Enums.Room_Type.Battle_Royale);
            Navigation.findNavController(v).navigate(action);
        });
        TabLayout menu = binding.getRoot().findViewById(R.id.menu_tab);
        //头像
        ImageView head_image = binding.getRoot().findViewById(R.id.head_image);
        head_image.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(Main_FragmentDirections.actionMainDestToPersonalFragment()));
        Button friends_button = binding.getRoot().findViewById(R.id.friends_button);
        friends_button.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(Main_FragmentDirections.actionMainDestToFriendFragment());
        });
        //菜单栏
        menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:break;
                    case 1:{
                        Navigation.findNavController(binding.getRoot()).navigate(Main_FragmentDirections.actionMainDestToRepositoryFragment());
                        break;
                    }
                    case 2: {
                        Navigation.findNavController(binding.getRoot()).navigate(Main_FragmentDirections.actionMainDestToBattleRepositoryFragment());
                        break;
                    }
                    default:break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //好友
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.friends_recycle);
        Friend_Adapt friend_adapt = new Friend_Adapt();
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
    }
}
