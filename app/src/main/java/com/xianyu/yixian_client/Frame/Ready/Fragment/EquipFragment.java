package com.xianyu.yixian_client.Frame.Ready.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.databinding.ReadyEquipFragmentBinding;
import com.xianyu.yixian_client.databinding.ReadyReadyFragmentBinding;

import io.reactivex.disposables.CompositeDisposable;

public class EquipFragment extends Fragment {
    private ReadyEquipFragmentBinding binding;
    private ReadyViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReadyEquipFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ReadyViewModel.class);
        init();
        return binding.getRoot();
    }
    private void init() {

    }
}
