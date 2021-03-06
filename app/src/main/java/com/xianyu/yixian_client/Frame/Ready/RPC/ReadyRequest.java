package com.xianyu.yixian_client.Frame.Ready.RPC;

import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.User;
import com.yixian.material.EtherealC.Annotation.RPCRequest;

import java.util.ArrayList;

public interface ReadyRequest {
    @RPCRequest
    Boolean InviteSquad(Long id);
    @RPCRequest
    ArrayList<User> EnterSquad(Long id,String secretKey);
    @RPCRequest
    void StartMatch();
    @RPCRequest
    void SwitchCardGroup(CardGroup cardGroup);
    @RPCRequest
    Boolean ConfirmCardGroup();
}
