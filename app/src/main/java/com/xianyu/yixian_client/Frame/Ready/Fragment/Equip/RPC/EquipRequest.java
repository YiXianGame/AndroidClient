package com.xianyu.yixian_client.Frame.Ready.Fragment.Equip.RPC;

import com.yixian.material.Entity.CardGroup;
import com.yixian.material.EtherealC.Annotation.RPCRequest;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Ready.RPC
 * @ClassName: EquipRequest
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 12:35
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 12:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface EquipRequest {
    @RPCRequest
    void SwitchCardGroup(CardGroup cardGroup);
    @RPCRequest
    Boolean ConfirmCardGroup();
}
