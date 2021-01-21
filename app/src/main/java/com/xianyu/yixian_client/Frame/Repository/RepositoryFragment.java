package com.xianyu.yixian_client.Frame.Repository;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.xianyu.yixian_client.Frame.Repository.Adapt.CardAdapt;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
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
        cardAdapt.setAnimationFirstOnly(false);
        cardAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        cardAdapt.setDiffCallback(new DiffUtil.ItemCallback<SkillCard>() {
            @Override
            public boolean areItemsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
                return oldItem.getId() == newItem.getId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull SkillCard oldItem, @NonNull SkillCard newItem) {
                return oldItem.getUpdate() == newItem.getUpdate();
            }
        });
        BaseLoadMoreModule loadMoreModule = cardAdapt.getLoadMoreModule();
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(4);
        loadMoreModule.setOnLoadMoreListener(() -> {
            if(viewModel.skillcards_live.getValue() == null)loadMoreModule.loadMoreFail();
            int last_index = viewModel.skillcards_live.getValue().lastIndexOf(cardAdapt.getData().get(cardAdapt.getData().size() - 1));
            if(last_index + 4 <= viewModel.skillcards_live.getValue().size()){
                cardAdapt.addData(new ArrayList<>(viewModel.skillcards_live.getValue().subList(last_index + 1,last_index + 4)));
                loadMoreModule.loadMoreComplete();
            }
            else if(last_index + 1 != viewModel.skillcards_live.getValue().size()){
                cardAdapt.addData(new ArrayList<>(viewModel.skillcards_live.getValue().subList(last_index + 1,viewModel.skillcards_live.getValue().size())));
                loadMoreModule.loadMoreComplete();
            }
            else{
                loadMoreModule.loadMoreEnd();
            }
        });
        viewModel.skillcards_live.observe(getViewLifecycleOwner(), list -> {
            if (viewModel.skillcards_live.getValue() != null){
                if(viewModel.skillcards_live.getValue().size() >= 9){
                    cardAdapt.setDiffNewData(new ArrayList<>(viewModel.skillcards_live.getValue().subList(0,9)));
                }
                else cardAdapt.setDiffNewData(new ArrayList<>(viewModel.skillcards_live.getValue()));
            }
            else cardAdapt.setDiffNewData(new ArrayList<>());
        });
        viewModel.refreshSkillCards();
    }
}