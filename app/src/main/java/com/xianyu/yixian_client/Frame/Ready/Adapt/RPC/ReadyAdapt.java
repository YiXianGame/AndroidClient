package com.xianyu.yixian_client.Frame.Ready.Adapt.RPC;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.xianyu.yixian_client.Frame.Ready.ReadyFragment;
import com.xianyu.yixian_client.Frame.Ready.ReadyViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyReadyFragmentBinding;
import com.yixian.make.Core;
import com.yixian.make.Event.ReadyEvent.Args.MatchSuccessArgs;
import com.yixian.material.Entity.User;
import com.yixian.material.RPC.Annotation.RPCMethod;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ReadyAdapt {
    private ReadyReadyFragmentBinding binding;
    private ReadyViewModel viewModel;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public ReadyAdapt(ReadyReadyFragmentBinding binding,ReadyViewModel viewModel) {
        this.binding = binding;
        this.viewModel = viewModel;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disposable.clear();
    }

    @RPCMethod
    public void StartMatch(){
        binding.getRoot().post(()->{
            //Core.readyEvent.OnEvent("UserAdapt",new StartMatchArgs());
            MessageDialog.Confirm_Dialog(binding.getRoot().getContext(),"[匹配系统]","开始匹配");
        });
    }
    @RPCMethod(parameters = "users-users-int-string-string-string")
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


        binding.getRoot().post(()->{
            new MaterialAlertDialogBuilder(binding.getRoot().getContext())
                    .setTitle("匹配成功")
                    .setMessage("即将进入卡组配置界面")
                    .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_readyFragment_to_equipFragment);
                    })
                    .show();
        });
    }
    @RPCMethod(parameters = "user-users")
    public void RefreshSquad(User user, ArrayList<User> users){

    }
    @RPCMethod
    public void InviteSquad(User inviter,String secretKey){
        new MaterialAlertDialogBuilder(binding.getRoot().getContext())
                .setTitle("[邀请系统]")
                .setMessage("您收到来自"+ inviter .getNickname()+"的邀请")
                .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                    disposable.add(Core.repository.userRepository.enterSquad(inviter.getId(),secretKey).subscribe(new Consumer<ArrayList<User>>() {
                        @Override
                        public void accept(ArrayList<User> users) throws Exception {
                            if(users!=null){
                                Core.liveSquad.setValue(users);
                            }
                        }
                    }));
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_readyFragment_to_equipFragment);
                })
                .setNegativeButton(R.string.reject_dialog,((dialog, which) -> {

                }))
                .show();
    }

}
