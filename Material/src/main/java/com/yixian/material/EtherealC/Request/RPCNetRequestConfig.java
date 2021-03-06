package com.yixian.material.EtherealC.Request;

import com.yixian.material.EtherealC.Model.RPCType;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.yixian.material.RPC
 * @ClassName: RPCNetRequestConfig
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 18:07
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 18:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RPCNetRequestConfig {
    private Boolean tokenEnable;
    private RPCType type;

    public RPCNetRequestConfig(RPCType type){
        this.type = type;
    }

    public Boolean getTokenEnable() {
        return tokenEnable;
    }

    public void setTokenEnable(Boolean tokenEnable) {
        this.tokenEnable = tokenEnable;
    }

    public RPCType getType() {
        return type;
    }

    public void setType(RPCType type) {
        this.type = type;
    }
}
