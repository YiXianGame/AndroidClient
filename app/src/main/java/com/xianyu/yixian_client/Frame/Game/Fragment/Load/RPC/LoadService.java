package com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC;

import com.xianyu.yixian_client.Frame.Game.Fragment.Load.Load_Fragment;
import com.xianyu.yixian_client.Frame.Game.GameViewModel;
import com.xianyu.yixian_client.Model.MessageDialog;
import com.xianyu.yixian_client.databinding.GameLoadFragmentBinding;
import com.yixian.material.Entity.Player;
import com.yixian.material.Entity.Team;
import com.yixian.material.EtherealC.Annotation.RPCService;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC
 * @ClassName: LoadService
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 12:31
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 12:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoadService {
    private Load_Fragment fragment;

    public LoadService(Load_Fragment fragment) {
        this.fragment = fragment;
    }

    @RPCService
    public void StartGame(ArrayList<Team> teams){
        fragment.getView().post(()->{
            fragment.getViewModel().getTeams().setValue(teams);
            MessageDialog.Confirm_Dialog(fragment.getContext(),"[加载系统]","进入游戏画面");
        });
    }
}
