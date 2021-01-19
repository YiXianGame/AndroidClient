package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainMainFragmentBinding;

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
        menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:break;
                    case 1:break;
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
    }
}
