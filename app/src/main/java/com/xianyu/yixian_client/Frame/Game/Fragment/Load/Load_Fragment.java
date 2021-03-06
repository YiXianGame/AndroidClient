package com.xianyu.yixian_client.Frame.Game.Fragment.Load;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC.LoadService;
import com.xianyu.yixian_client.Frame.Game.GameViewModel;
import com.xianyu.yixian_client.databinding.GameLoadFragmentBinding;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Team;
import com.yixian.material.Entity.User;
import com.yixian.material.Exception.RPCException;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Model.RPCType;

import java.util.ArrayList;

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
        RPCNetServiceFactory.register(new LoadService(this),"LoadService", Core.playerServer.first,Core.playerServer.second,type);

        viewModel.loadRequest.Connect(Core.liveUser.getValue().getId(),Core.liveUser.getValue().getPassword());
    }
}

