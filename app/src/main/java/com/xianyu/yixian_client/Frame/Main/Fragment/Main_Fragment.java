package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
        if(binding == null){
            Log.d(Tag.Debug,"重新创建");
            binding = MainMainFragmentBinding.inflate(inflater,container,false);
            viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            init();
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
    private void init() {
        //音频初始化
        MediaPlayer mediaPlayer = MediaPlayer.create(requireContext(),R.raw.b);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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
    }
}
