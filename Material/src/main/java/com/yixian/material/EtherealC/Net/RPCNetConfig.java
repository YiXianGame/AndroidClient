package com.yixian.material.EtherealC.Net;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.yixian.material.RPC
 * @ClassName: RPCNetConfig
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/5 18:10
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/5 18:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RPCNetConfig {
    private String host;
    private String port;
    private int bufferSize = 1024;

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
