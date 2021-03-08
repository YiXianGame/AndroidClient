package com.xianyu.yixian_client.Frame.Ready.RPC;

import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xianyu.yixian_client.Frame.Ready.ReadyFragment;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Player;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Annotation.RPCService;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ReadyService {
    private ReadyFragment fragment;
    private final CompositeDisposable disposable = new CompositeDisposable();
    boolean sure = false;
    public ReadyService(ReadyFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void finalize() throws Throwable {
        disposable.clear();
        super.finalize();
    }

    @RPCService
    public void StartMatch(){
        fragment.getView().post(()->{
            //Core.readyEvent.OnEvent("UserService",new StartMatchArgs());
            MessageDialog.Confirm_Dialog(fragment.getView().getContext(),"[匹配系统]","开始匹配");
        });
    }
    @RPCService(parameters = "List<Team>")
    public void MatchSuccess(ArrayList<Team> teams){
        if(sure == false)sure = true;
        else return;
        fragment.getView().post(()->{
            fragment.getViewModel().getLiveTeams().setValue(teams);
            for(Team team : teams){
                if(team.getTeammates().containsKey(Core.liveUser.getValue().getId())){
                    fragment.getViewModel().setPlayer(team.getTeammates().get(Core.liveUser.getValue().getId()));
                }
                team.getTeammates().values().forEach(item->item.setTeam(team));
            }
            new MaterialAlertDialogBuilder(fragment.getView().getContext())
                    .setTitle("匹配成功")
                    .setMessage("即将进入卡组配置界面")
                    .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                        Navigation.findNavController(fragment.getView()).navigate(R.id.action_readyFragment_to_equipFragment);
                    })
                    .show();
        });
    }
    @RPCService(parameters = {"List<User>"})
    public void RefreshSquad(ArrayList<User> users){
        Core.liveSquad.postValue(users);
    }
    @RPCService
    public void InviteSquad(User inviter,String secretKey){
        fragment.getView().post(()->{
            new MaterialAlertDialogBuilder(fragment.getView().getContext())
                    .setTitle("[邀请系统]")
                    .setMessage("您收到来自"+ inviter .getNickname()+"的邀请")
                    .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                        Single.create((SingleOnSubscribe<User>) emitter -> {
                            ArrayList<User> users = fragment.getViewModel().getReadyRequest().EnterSquad(inviter.getId(),secretKey);
                            if(users!=null){
                                Core.liveSquad.postValue(users);
                                Navigation.findNavController(fragment.getView()).navigate(R.id.action_readyFragment_to_equipFragment);
                            }
                        }).subscribeOn(Schedulers.io()).subscribe();
                    })
                    .setNegativeButton(R.string.reject_dialog,((dialog, which) -> {

                    }))
                    .show();
        });
    }
    @RPCService
    public void SwitchCardGroup(Long id, CardGroup cardGroup){
        for (Team team : fragment.getViewModel().getLiveTeams().getValue()){
            if(team.getTeammates().containsKey(id)){
                team.getTeammates().get(id).setCardGroup(cardGroup);
                break;
            }
        }
        fragment.getViewModel().getLiveTeams().postValue(fragment.getViewModel().getLiveTeams().getValue());
    }
}
