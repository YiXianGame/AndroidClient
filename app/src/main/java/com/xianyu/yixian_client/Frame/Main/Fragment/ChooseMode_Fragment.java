package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.MainChooseModeFragmentBinding;
import com.yixian.material.Entity.Room;

import io.reactivex.disposables.CompositeDisposable;


public class ChooseMode_Fragment extends Fragment {
    private MainChooseModeFragmentBinding binding;
    private MainViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainChooseModeFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        init();
        return binding.getRoot();
    }

    private void init() {
        Button solo_button = binding.getRoot().findViewById(R.id.soloGame_button);
        solo_button.setOnClickListener(v ->{
            ChooseMode_FragmentDirections.ActionChooseModeDestToReadyFragment action = ChooseMode_FragmentDirections.actionChooseModeDestToReadyFragment(Room.RoomType.Round_Solo);
            if(ChooseMode_FragmentArgs.fromBundle(getArguments()).getGameMode() == 0){
                action.setRoomMode(Room.RoomType.Round_Solo);
            }
            else {
                action.setRoomMode(Room.RoomType.RealTime_Solo);
            }
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
