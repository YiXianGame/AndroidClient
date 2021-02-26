package com.xianyu.yixian_client.Frame.Ready.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Equip_User_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Equip_Group_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.RPC.ReadyAdapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Section.CardGroupSectionFirstNode;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyEquipFragmentBinding;
import com.yixian.make.Core;
import com.yixian.make.RPC.Adapt.UserAdapt;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.RPC.RPCAdaptFactory;
import com.yixian.material.RPC.RPCType;

import java.util.ArrayList;
import java.util.List;

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
        RecyclerView teammates_recycler = binding.getRoot().findViewById(R.id.teammates_recycler);
        Equip_User_Adapt teammates_user_adapt = new Equip_User_Adapt(viewModel);
        teammates_user_adapt.setDiffNewData(new ArrayList<>(viewModel.liveTeammates.getValue().values()));
        teammates_recycler.setAdapter(teammates_user_adapt);

        RecyclerView enemies_recycler = binding.getRoot().findViewById(R.id.enemies_recycler);
        Equip_User_Adapt enemies_user_adapt = new Equip_User_Adapt(viewModel);
        enemies_user_adapt.setDiffNewData(new ArrayList<>(viewModel.liveEnemies.getValue().values()));
        enemies_recycler.setAdapter(enemies_user_adapt);

        RecyclerView group_recycle = binding.getRoot().findViewById(R.id.cardGroups_recycler);
        Equip_Group_Adapt groupAdapter = new Equip_Group_Adapt();
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
            if(!viewModel.sure){
                viewModel.liveTeammates.getValue().get(Core.liveUser.getValue().getId()).setCardGroup(((CardGroupSectionFirstNode) group_adapt.getItem(position)).getCardGroup());
                viewModel.liveTeammates.postValue(viewModel.liveEnemies.getValue());
                viewModel.sure = true;
            }
        });
    }
}
