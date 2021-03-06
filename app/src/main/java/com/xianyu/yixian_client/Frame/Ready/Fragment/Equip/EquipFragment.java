package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt.Equip_User_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt.Equip_Group_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.RPC.EquipService;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyEquipFragmentBinding;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class EquipFragment extends Fragment {
    private ReadyEquipFragmentBinding binding;
    private ReadyViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public ReadyViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReadyEquipFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ReadyViewModel.class);
        init();
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        RPCNetServiceFactory.destory("EquipService",Core.userServer.first,Core.userServer.second);
        RPCNetRequestFactory.destory("EquipRequest",Core.userServer.first,Core.userServer.second);
        viewModel.equipRequest = null;
        super.onDestroy();
    }

    private void init() {
        //初始化RPC
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
        RPCNetServiceFactory.register(new EquipService(this),"EquipService",Core.userServer.first,Core.userServer.second,type);
        RecyclerView teammates_recycler = binding.getRoot().findViewById(R.id.teammates_recycler);
        Equip_User_Adapt teammates_user_adapt = new Equip_User_Adapt(viewModel);
        viewModel.liveTeammates.observe(getViewLifecycleOwner(),value->{
            teammates_user_adapt.setDiffNewData(new ArrayList<>(value.values()));
            teammates_recycler.setAdapter(teammates_user_adapt);
        });

        RecyclerView enemies_recycler = binding.getRoot().findViewById(R.id.enemies_recycler);
        Equip_User_Adapt enemies_user_adapt = new Equip_User_Adapt(viewModel);
        viewModel.liveEnemies.observe(getViewLifecycleOwner(),value->{
            enemies_user_adapt.setDiffNewData(new ArrayList<>(value.values()));
            enemies_recycler.setAdapter(enemies_user_adapt);
        });

        RecyclerView group_recycle = binding.getRoot().findViewById(R.id.cardGroups_recycler);
        Equip_Group_Adapt groupAdapter = new Equip_Group_Adapt(viewModel);
        groupAdapter.setAnimationFirstOnly(false);
        groupAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        group_recycle.setAdapter(groupAdapter);

        Core.liveUser.observe(requireActivity(), owner -> {
            List<BaseNode> roots = new ArrayList<>();
            for(CardGroup item : owner.getCardGroups()){
                roots.add(new CardGroupSectionFirstNode(item));
            }
            groupAdapter.setDiffNewData(roots);
        });

        groupAdapter.setOnItemClickListener((group_adapt, view, position) -> {
            ((Equip_Group_Adapt)group_adapt).expandOrCollapse(position);
        });

        Button confirm_button = binding.getRoot().findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(v -> {
            if(viewModel.liveTeammates.getValue().get(Core.liveUser.getValue().getId()).getCardGroup()!=null){
                viewModel.confirmCardGroup().as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(value->{
                    if(value){
                        viewModel.confirm = true;
                        confirm_button.setEnabled(false);
                    }
                    else viewModel.confirm = false;
                });
            }
            else MessageDialog.Error_Dialog(getContext(),"[备战系统]","您当前未选择卡组");
        });
    }
}
