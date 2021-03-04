package com.xianyu.yixian_client.Frame.Ready.RPC;

import android.view.View;

import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Player;
import com.yixian.material.Entity.Room;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCService;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;

public class ReadyService {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private ReadyViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public ReadyService(ReadyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disposable.clear();
    }

    @RPCService
    public void StartMatch(){
        view.post(()->{
            //Core.readyEvent.OnEvent("UserService",new StartMatchArgs());
            MessageDialog.Confirm_Dialog(view.getContext(),"[匹配系统]","开始匹配");
        });
    }
    @RPCService(parameters = "List<Team>")
    public void MatchSuccess(ArrayList<Team> teams){
        view.post(()->{
            viewModel.liveTeammates.setValue(new HashMap<>());
            viewModel.liveEnemies.setValue(new HashMap<>());
            for (Team team : teams) {
                if (team.getTeammates().containsKey(Core.liveUser.getValue().getId())) {
                    for (Player player : team.getTeammates().values()){
                        viewModel.liveTeammates.getValue().put(player.getId(),player);
                    }
                }
                else {
                    for (Player player : team.getTeammates().values()){
                        viewModel.liveEnemies.getValue().put(player.getId(),player);
                    }
                }
            }
            new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("匹配成功")
                    .setMessage("即将进入卡组配置界面")
                    .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                        Navigation.findNavController(view).navigate(R.id.action_readyFragment_to_equipFragment);
                    })
                    .show();
        });
    }
    @RPCService(parameters = {"List<User>"})
    public void RefreshSquad(ArrayList<User> users){

    }
    @RPCService
    public void InviteSquad(User inviter,String secretKey){
        new MaterialAlertDialogBuilder(view.getContext())
                .setTitle("[邀请系统]")
                .setMessage("您收到来自"+ inviter .getNickname()+"的邀请")
                .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                    disposable.add(viewModel.enterSquad(inviter.getId(),secretKey).subscribe(users -> {
                        if(users!=null){
                            Core.liveSquad.setValue(users);
                            Navigation.findNavController(view).navigate(R.id.action_readyFragment_to_equipFragment);
                        }
                    }));
                })
                .setNegativeButton(R.string.reject_dialog,((dialog, which) -> {

                }))
                .show();
    }
    @RPCService
    public void SwitchCardGroup(Long id,CardGroup cardGroup){
        if(viewModel.liveTeammates.getValue().containsKey(id)){
            viewModel.liveTeammates.getValue().get(id).setCardGroup(cardGroup);
            viewModel.liveTeammates.setValue(viewModel.liveTeammates.getValue());
        }
        else {
            viewModel.liveEnemies.getValue().get(id).setCardGroup(cardGroup);
            viewModel.liveEnemies.setValue(viewModel.liveTeammates.getValue());
        }
    }
    @RPCService
    public void ConnectPlayerServer(String ip,String port,String roomId){

    }
}
