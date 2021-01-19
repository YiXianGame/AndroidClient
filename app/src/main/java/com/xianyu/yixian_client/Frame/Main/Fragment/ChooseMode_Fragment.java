package com.xianyu.yixian_client.Frame.Main.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.Frame.Main.MainViewModel;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.databinding.MainChooseModeFragmentBinding;

import io.reactivex.disposables.CompositeDisposable;


public class ChooseMode_Fragment extends Fragment {
    private MainChooseModeFragmentBinding binding;
    private MainViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(binding == null){
            binding = MainChooseModeFragmentBinding.inflate(inflater,container,false);
            viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
