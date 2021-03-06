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
import com.xianyu.yixian_client.Frame.Ready.Adapt.Ready_Friend_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Ready_User_Adapt;
import com.xianyu.yixian_client.Frame.Ready.RPC.ReadyService;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyReadyFragmentBinding;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Service.RPCNetServiceConfig;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.disposables.CompositeDisposable;

@AndroidEntryPoint
public class ReadyFragment extends Fragment {
    private ReadyReadyFragmentBinding binding;
    private ReadyViewModel viewModel;

    @Inject
    Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public ReadyViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReadyReadyFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ReadyViewModel.class);
        viewModel.initialization(repository);
        assert getArguments() != null;
        viewModel.setRoomType(ReadyFragmentArgs.fromBundle(requireArguments()).getRoomMode());
        if(Core.liveSquad.getValue()==null)Core.liveSquad.setValue(new ArrayList<>());
        else Core.liveSquad.getValue().clear();
        Core.liveSquad.getValue().add(Core.liveUser.getValue());
        init();
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        RPCNetServiceFactory.destory("ReadyService",Core.userServer.first,Core.userServer.second);
        RPCNetRequestFactory.destory("ReadyRequest",Core.userServer.first,Core.userServer.second);
        viewModel.setReadyRequest(null);
        super.onDestroy();
    }

    private void init() {
        //?????????RPC
        RPCType type = new RPCType();
        try{
            type.add(Integer.class,"Int");
            type.add(String.class,"String");
            type.add(Boolean.class,"Bool");
            type.add(Long.class,"Long");
            type.add(User.class,"User");
            type.add(CardGroup.class,"CardGroup");
            type.add(new ArrayList<Long>(){}.getClass().getGenericSuperclass(),"List<long>");
            type.add(new ArrayList<User>(){}.getClass().getGenericSuperclass(),"List<User>");
            type.add(new ArrayList<Team>(){}.getClass().getGenericSuperclass(),"List<Team>");
        } catch (RPCException e) {
            e.printStackTrace();
        }
        RPCNetServiceConfig config = new RPCNetServiceConfig(type);
        RPCNetServiceFactory.register(new ReadyService(this),"ReadyClient",Core.userServer.first,Core.userServer.second,config);

        //??????
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.invite_recycle);
        Ready_Friend_Adapt friend_adapt = new Ready_Friend_Adapt(viewModel);
        BaseLoadMoreModule loadMoreModule = friend_adapt.getLoadMoreModule();
        friend_adapt.setAnimationEnable(true);
        friend_adapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);
        friend_adapt.setAnimationFirstOnly(false);
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreEndClick(false);
        loadMoreModule.setPreLoadNumber(1);
        loadMoreModule.setOnLoadMoreListener(() -> {
            List<User> users = friend_adapt.filter(Objects.requireNonNull(Core.liveFriends.getValue()));
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
            List<User> users = friend_adapt.filter(Objects.requireNonNull(Core.liveFriends.getValue()));
            if (users != null){
                if(users.size() >= 9){
                    friend_adapt.setDiffNewData(users.subList(0,9));
                }
                else friend_adapt.setDiffNewData(users);
            }
            else friend_adapt.setDiffNewData(new ArrayList<>());
        });
        recyclerView.setAdapter(friend_adapt);

        //??????
        recyclerView = binding.getRoot().findViewById(R.id.pos_recycle);
        Ready_User_Adapt readyUser_adapt= new Ready_User_Adapt(viewModel);
        Core.liveSquad.observe(getViewLifecycleOwner(), users ->  {
            readyUser_adapt.setDiffNewData(Core.liveSquad.getValue());
        });
        recyclerView.setAdapter(readyUser_adapt);

        Button match_button = binding.getRoot().findViewById(R.id.match_button);
        match_button.setOnClickListener(v -> viewModel.startMatch());
    }
}
