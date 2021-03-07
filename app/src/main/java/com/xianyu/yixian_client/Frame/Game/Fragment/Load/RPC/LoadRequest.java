package com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.Team;
import com.yixian.material.EtherealC.Annotation.RPCRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Game.Fragment.Load.RPC
 * @ClassName: LoadRequest
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 12:31
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 12:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface LoadRequest {
    @RPCRequest
    ArrayList<Team> Connect(long id,String password);
    @RPCRequest
    ArrayList<SkillCard> SyncSkillCard(ArrayList<SkillCard> skillCards);
    @RPCRequest
    void SyncSkillCardSuccess();
}
