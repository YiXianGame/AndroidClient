package com.xianyu.yixian_client.Frame.Game.Fragment.Load;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.Game.Fragment.Load.Adapt.Load_Player_Adapt;
import com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC.LoadService;
import com.xianyu.yixian_client.Frame.Game.GameViewModel;
import com.xianyu.yixian_client.databinding.GameLoadFragmentBinding;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Service.RPCNetServiceConfig;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Game.Fragment
 * @ClassName: Load_Fragment
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/4 20:01
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/4 20:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Load_Fragment extends Fragment {
    private GameLoadFragmentBinding binding;
    private GameViewModel viewModel;
    public GameViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = GameLoadFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        init();
        return binding.getRoot();
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
        RPCNetServiceFactory.register(new LoadService(this),"LoadClient", Core.playerServer.first,Core.playerServer.second,new RPCNetServiceConfig(type));
        viewModel.connect(Core.liveUser.getValue().getId(),Core.liveUser.getValue().getPassword()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(teams->{
            viewModel.getTeams().setValue(teams);
            ArrayList<SkillCard> skillCards = new ArrayList<>();
            for (long id : viewModel.getPlayer().getCardGroup().getCards()){
                if(Core.liveSkillcards.getValue().containsKey(id)){
                    skillCards.add(Core.liveSkillcards.getValue().get(id));
                }
                else {
                    SkillCard skillCard = new SkillCard();
                    skillCard.setId(id);
                    skillCard.setAttributeUpdate(-2);
                    skillCards.add(skillCard);
                }
            }
            viewModel.syncSkillCard(skillCards).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(value ->{
                if(value!=null){
                    for(SkillCard skillCard : skillCards){
                        if(skillCard.getAttributeUpdate() == -2){
                            Core.liveSkillcards.getValue().remove(skillCard.getId());
                            Core.repository.skillCardRepository.delete(skillCard);
                        }
                        else {
                            Core.liveSkillcards.getValue().remove(skillCard.getId());
                            Core.liveSkillcards.getValue().put(skillCard.getId(),skillCard);
                            Core.repository.skillCardRepository.insert(skillCard);
                        }
                    }
                }
                viewModel.loadRequest.SyncSkillCardSuccess();
            });
        });

        viewModel.getTeams().observe(getViewLifecycleOwner(),value->{
            for (Team item : value){
                if(item.getTeammates().containsKey(Core.liveUser.getValue().getId())){
                    viewModel.setPlayer(viewModel.getPlayer());
                }
                item.getTeammates().values().forEach(player -> player.setTeam(item));
            }
            LinearLayout teammates_layout = binding.teammatesLayout;
            LinearLayout enemies_layout = binding.enemiesLayout;
            teammates_layout.removeAllViews();
            enemies_layout.removeAllViews();
            for (Team team : value){
                RecyclerView recyclerView = new RecyclerView(getContext());
                Load_Player_Adapt adapt = new Load_Player_Adapt();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapt.setDiffNewData(new ArrayList<>(team.getTeammates().values()));
                if(team.getTeammates().containsKey(Core.liveUser.getValue().getId())){
                    teammates_layout.addView(recyclerView);
                }
                else {
                    enemies_layout.addView(recyclerView);
                }
            }
        });
    }
}

