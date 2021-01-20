package com.xianyu.yixian_client.Frame.Repository;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianyu.yixian_client.Frame.Repository.Adapt.CardAdapt;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.RepositoryFragmentBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RepositoryFragment extends Fragment {
    @Inject
    Repository repository;
    RepositoryViewModel viewModel;
    RepositoryFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = RepositoryFragmentBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(RepositoryViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }

    private void init() {
        CardAdapt cardAdapt = new CardAdapt(new ArrayList<>());
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.card_frame);
        recyclerView.setAdapter(cardAdapt);
        viewModel.skillcards_live.observe(getViewLifecycleOwner(), cardAdapt::setOrigin_data);
        viewModel.refreshSkillCards();
    }
}