package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.RPC;

import android.content.Intent;
import android.util.Pair;

import androidx.fragment.app.Fragment;

import com.xianyu.yixian_client.Frame.Game.Game_Activity;
import com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.EquipFragment;
import com.yixian.make.Core;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.Team;
import com.yixian.material.EtherealC.Annotation.RPCService;
import com.yixian.material.EtherealC.Request.RPCNetRequestFactory;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;

import java.util.Objects;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Ready.RPC
 * @ClassName: EquipService
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 12:35
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 12:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EquipService extends Fragment{
    private EquipFragment fragment;

    public EquipService(EquipFragment fragment) {
        this.fragment = fragment;
    }

    @RPCService
    public void SwitchCardGroup(Long id, CardGroup cardGroup){
        for(Team team : Objects.requireNonNull(fragment.getViewModel().getLiveTeams().getValue())){
            if(team.getTeammates().containsKey(id)){
                Objects.requireNonNull(team.getTeammates().get(id)).setCardGroup(cardGroup);
                fragment.getViewModel().getLiveTeams().postValue(fragment.getViewModel().getLiveTeams().getValue());
            }
        }
    }
    @RPCService
    public void ConnectPlayerServer(String ip,String port){
        Core.playerServer = new Pair<>(ip,port);
        RPCNetServiceFactory.destory("ReadyClient", Core.userServer.first,Core.userServer.second);
        RPCNetRequestFactory.destory("ReadyServer", Core.userServer.first,Core.userServer.second);
        Intent intent = new Intent();
        intent.setClass(fragment.getContext(), Game_Activity.class);
        fragment.startActivity(intent);
        fragment.requireActivity().finish();
        fragment = null;
    }
}
