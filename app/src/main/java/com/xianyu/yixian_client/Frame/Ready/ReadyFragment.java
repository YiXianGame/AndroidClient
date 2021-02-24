package com.xianyu.yixian_client.Frame.Ready;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Ready_Friend_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Adapt.Ready_User_Adapt;
import com.xianyu.yixian_client.Frame.Ready.Model.UserWithCardGroupItem;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.ReadyReadyFragmentBinding;
import com.yixian.make.Core;
import com.yixian.make.Event.ReadyEvent.Args.InviteUserArgs;
import com.yixian.make.Event.ReadyEvent.Args.MatchSuccessArgs;
import com.yixian.make.Event.ReadyEvent.ReadyDelegate;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ReadyFragment extends Fragment {
    private ReadyReadyFragmentBinding binding;
    private ReadyViewModel viewModel;
    private ReadyDelegate readyDelegate;
    @Inject
    Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReadyReadyFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(ReadyViewModel.class);
        viewModel.initialization(repository);
        assert getArguments() != null;
        viewModel.roomType = ReadyFragmentArgs.fromBundle(requireArguments()).getRoomMode();
        viewModel.liveTeammates.setValue(new HashMap<>());
        viewModel.liveEnemies.setValue(new HashMap<>());
        if(Core.liveSquad.getValue()==null)Core.liveSquad.setValue(new ArrayList<>());
        else Core.liveSquad.getValue().clear();
        Core.liveSquad.getValue().add(Core.liveUser.getValue());
        init();
        return binding.getRoot();
    }
    private void init() {
        //好友
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

        //玩家
        recyclerView = binding.getRoot().findViewById(R.id.pos_recycle);
        Ready_User_Adapt readyUser_adapt= new Ready_User_Adapt(viewModel);
        Core.liveSquad.observe(getViewLifecycleOwner(), users ->  {
            readyUser_adapt.setDiffNewData(Core.liveSquad.getValue());
        });
        recyclerView.setAdapter(readyUser_adapt);

        //注册事件监听
        readyDelegate = (sender, args) -> {
            if(args instanceof MatchSuccessArgs){
                MatchSuccessArgs matchSuccessArgs = (MatchSuccessArgs) args;
                ArrayList<User> teammates;
                ArrayList<User> enemies;
                if(matchSuccessArgs.getIdx() == 0){
                    teammates = matchSuccessArgs.getGroup_1();
                    enemies = matchSuccessArgs.getGroup_2();
                }
                else {
                    teammates = matchSuccessArgs.getGroup_2();
                    enemies = matchSuccessArgs.getGroup_1();
                }
                viewModel.liveEnemies.getValue().clear();
                viewModel.liveTeammates.getValue().clear();
                for (User item: teammates) {
                    viewModel.liveTeammates.getValue().replace(item.getId(),new UserWithCardGroupItem(item));
                }
                viewModel.liveTeammates.postValue(viewModel.liveTeammates.getValue());
                for (User item: enemies) {
                    viewModel.liveEnemies.getValue().replace(item.getId(),new UserWithCardGroupItem(item));
                }

                viewModel.liveEnemies.postValue(viewModel.liveEnemies.getValue());

                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("匹配成功")
                        .setMessage("即将进入卡组配置界面")
                        .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_readyFragment_to_equipFragment);
                        })
                        .show();
            }
            else if(args instanceof InviteUserArgs){
                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("[邀请系统]")
                        .setMessage("您收到来自"+((InviteUserArgs) args).getInviter().getNickname()+"的邀请")
                        .setPositiveButton(R.string.confirm_dialog, (dialog, which) -> {
                            Core.repository.userRepository.enterSquad(((InviteUserArgs) args).getInviter().getId(),((InviteUserArgs) args).getSecretKey())
                                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(getLifecycle()))).subscribe(new Consumer<ArrayList<User>>() {
                                @Override
                                public void accept(ArrayList<User> users) throws Exception {
                                    if(users!=null){
                                        Core.liveSquad.setValue(users);
                                    }
                                }
                            });
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_readyFragment_to_equipFragment);
                        })
                        .setNegativeButton(R.string.reject_dialog,((dialog, which) -> {

                        }))
                        .show();
            }
        };
        Core.readyEvent.register(readyDelegate);

        Button match_button = binding.getRoot().findViewById(R.id.match_button);
        match_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createSquad(viewModel.roomType.toString()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(getLifecycle()))).subscribe(value->{
                    if(!value.equals("-1")){
                        MessageDialog.Dialog(getContext(),"[匹配系统]","开始匹配");
                    }
                    else MessageDialog.Dialog(getContext(),"[匹配系统]","匹配失败");
                });
            }
        });
    }
}
