package com.xianyu.yixian_client.Frame.PersonalInformation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.Bind.PersonalInformation_Fragment_Adapter;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalProfileFragmentBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@AndroidEntryPoint
public class PersonalProfile_Fragment extends Fragment {
    PersonalProfileViewModel viewModel;
    PersonalProfileFragmentBinding binding;
    ViewPager2 paper;
    @Inject
    Repository repository;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PersonalProfileFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(PersonalProfileViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }

    private void init() {
        //fragment绑定初始化
        paper = binding.getRoot().findViewById(R.id.paper);
        paper.setPageTransformer(new DepthPageTransformer());
        paper.setAdapter(new PersonalInformation_Fragment_Adapter(requireActivity()));
    }

    public void personal_Click(View view) {
        paper.setCurrentItem(1);
    }

    public void history_Click(View view) {
        paper.setCurrentItem(0);
    }

    public void information_Click(View view) {
        paper.setCurrentItem(2);
    }
}