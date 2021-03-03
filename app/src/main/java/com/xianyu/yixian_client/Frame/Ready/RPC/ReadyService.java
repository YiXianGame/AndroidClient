package com.xianyu.yixian_client.Frame.Ready.RPC;

import android.view.View;

import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCService;

import java.util.ArrayList;

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
    @RPCService(parameters = "users-users-int-string-string-string")
    public void MatchSuccess(ArrayList<User> group_1, ArrayList<User> group_2, int idx, String hostname, String port, String secretKey){
        ArrayList<User> teammates;
        ArrayList<User> enemies;
        if(idx == 0){
            teammates = group_1;
            enemies = group_2;
        }
        else {
            teammates = group_2;
            enemies = group_1;
        }
        viewModel.liveEnemies.getValue().clear();
        viewModel.liveTeammates.getValue().clear();
        if(teammates!=null){
            for (User item: teammates) {
                viewModel.liveTeammates.getValue().put(item.getId(),new UserWithCardGroupItem(item));
            }
            viewModel.liveTeammates.postValue(viewModel.liveTeammates.getValue());
        }

        if(enemies!=null){
            for (User item: enemies) {
                viewModel.liveEnemies.getValue().put(item.getId(),new UserWithCardGroupItem(item));
            }
            viewModel.liveEnemies.postValue(viewModel.liveEnemies.getValue());
        }


        view.post(()->{
            new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("匹配成功")
                    .setMessage("即将进入卡组配置界面")
                    .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                        Navigation.findNavController(view).navigate(R.id.action_readyFragment_to_equipFragment);
                    })
                    .show();
        });
    }
    @RPCService(parameters = "user-users")
    public void RefreshSquad(User user, ArrayList<User> users){

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
    public void SwitchCardGroup(Long id,Boolean isTeammates,CardGroup cardGroup){
        if(isTeammates){
            viewModel.liveTeammates.getValue().get(id).setCardGroup(cardGroup);
            viewModel.liveTeammates.setValue(viewModel.liveTeammates.getValue());
        }
        else {
            viewModel.liveEnemies.getValue().get(id).setCardGroup(cardGroup);
            viewModel.liveEnemies.setValue(viewModel.liveTeammates.getValue());
        }
    }
}
