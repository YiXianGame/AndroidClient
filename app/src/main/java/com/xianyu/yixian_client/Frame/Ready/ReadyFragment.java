package com.xianyu.yixian_client.Frame.Ready;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.Ready.Adapt.ReadyInvite_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.ReadyUser_Adapt;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyReadyFragmentBinding;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ReadyFragment extends Fragment {
    private ReadyReadyFragmentBinding binding;
    private ReadyViewModel viewModel;
    @Inject
    Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReadyReadyFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ReadyViewModel.class);
        viewModel.initialization(repository);
        init();
        return binding.getRoot();
    }
    private void init() {
        //好友
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.invite_recycle);
        ReadyInvite_Adapt friend_adapt = new ReadyInvite_Adapt(viewModel);
        BaseLoadMoreModule loadMoreModule = friend_adapt.getLoadMoreModule();
        friend_adapt.setAnimationEnable(true);
        friend_adapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
        friend_adapt.setAnimationFirstOnly(false);
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(1);
        loadMoreModule.setOnLoadMoreListener(() -> {
            List<User> users = friend_adapt.filter(Core.liveFriends.getValue());
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
        Core.liveFriends.observe(getViewLifecycleOwner(), list -> {
            List<User> users = friend_adapt.filter(Core.liveFriends.getValue());
            if (users != null){
                if(users.size() >= 9){
                    friend_adapt.setDiffNewData(users.subList(0,9));
                }
                else friend_adapt.setDiffNewData(users);
            }
            else friend_adapt.setDiffNewData(new ArrayList<>());
        });
        recyclerView.setAdapter(friend_adapt);

        recyclerView = binding.getRoot().findViewById(R.id.pos_recycle);
        ReadyUser_Adapt readyUser_adapt= new ReadyUser_Adapt(viewModel);
        readyUser_adapt.setDiffNewData(new ArrayList<>());
        viewModel.liveReadyUsers.getValue().add(Core.liveUser.getValue());
        viewModel.liveReadyUsers.postValue(viewModel.liveReadyUsers.getValue());
        viewModel.liveReadyUsers.observe(getViewLifecycleOwner(),user ->  {
            readyUser_adapt.setDiffNewData(viewModel.liveReadyUsers.getValue());
        });
        recyclerView.setAdapter(readyUser_adapt);

        Button match_button = binding.getRoot().findViewById(R.id.match_button);
        match_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.startMatch(Core.liveUser.getValue().getId(), viewModel.roomType.toString()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(getLifecycle()))).subscribe(value->{
                    if(value){
                        MessageDialog.Dialog(getContext(),"[匹配系统]","开始匹配");
                    }
                    else MessageDialog.Dialog(getContext(),"[匹配系统]","匹配失败");
                });
            }
        });
    }
}
