package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Main.Adapt.Friend_Adapt;
import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainMainFragmentBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        viewModel.queryFriendUsers(12345).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(users -> {
                    //好友列表
                    RecyclerView recyclerView = binding.getRoot().findViewById(R.id.friends_recycle);
                    recyclerView.setAdapter(new Friend_Adapt(users));
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
        //好友栏
        viewModel.queryFriendUsers(Core.liveUser.getValue().getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(users -> {
                    RecyclerView friends_recycle = binding.getRoot().findViewById(R.id.friends_recycle);
                    friends_recycle.setAdapter(new Friend_Adapt(users));
                    Button friends_button = binding.getRoot().findViewById(R.id.friends_button);
                    friends_button.setOnClickListener(v -> {
                        if(friends_recycle.getVisibility() == View.VISIBLE)friends_recycle.setVisibility(View.INVISIBLE);
                        else friends_recycle.setVisibility(View.VISIBLE);
                    });
                });
    }
}
