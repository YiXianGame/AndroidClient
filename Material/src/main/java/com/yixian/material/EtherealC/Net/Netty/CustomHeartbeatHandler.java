package com.yixian.material.EtherealC.Net.Netty;

import android.util.Log;

import com.yixian.material.Log.Log.Tag;
import com.yixian.material.EtherealC.Model.ClientRequestModel;
import com.yixian.material.EtherealC.Model.ClientResponseModel;
import com.yixian.material.EtherealC.Service.RPCNetServiceFactory;
import com.yixian.material.EtherealC.Service.RPCNetService;
import com.yixian.material.EtherealC.Model.ServerRequestModel;

import java.lang.reflect.Method;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import kotlin.Triple;

/**
 * @author xiongyongshun
 * @version 1.0
 * @email yongshun1228@gmail.com
 * @created 16/9/18 13:02
 */
public class CustomHeartbeatHandler extends ChannelHandlerAdapter {

    private int heartbeatCount = 0;
    private SocketClient socketClient;
    public CustomHeartbeatHandler(SocketClient socketClient){
        this.socketClient = socketClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ClientResponseModel){
            ClientResponseModel respond = (ClientResponseModel) msg;
            ClientRequestModel request = socketClient.tasks.get(Integer.parseInt((respond.getId())));
            if(request != null){
                socketClient.tasks.remove(Long.parseLong((respond.getId())));
                request.setResult(respond);
            }
            else {
                Log.e(Tag.RemoteRepository,"\n------------------未找到请求--------------------");
                Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.getConfig().getHost(),socketClient.getConfig().getPort(),respond));
                Log.e(Tag.RemoteRepository,"--------------------------------------------");
            }
        }
        else if(msg instanceof ServerRequestModel){
            ServerRequestModel request = (ServerRequestModel)msg;
            RPCNetService adapt;
            Method method;
            adapt = RPCNetServiceFactory.services.get(new Triple<>(((ServerRequestModel) msg).Service,socketClient.getConfig().getHost(),socketClient.getConfig().getPort()));
            if(adapt != null){
                method = adapt.getMethods().get(request.MethodId);
                if(method!= null){
                    adapt.ConvertParams(request.MethodId,request.Params);
                    method.invoke(adapt.getInstance(),request.Params);
                }
                else {
                    Log.e(Tag.RemoteRepository,"\n------------------未找到该方法--------------------");
                    Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.getConfig().getHost(),socketClient.getConfig().getPort()));
                    Log.e(Tag.RemoteRepository,"--------------------------------------------");
                    return;
                }
            }
            else {
                Log.e(Tag.RemoteRepository,"\n------------------未找到该服务--------------------");
                Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.getConfig().getHost(),socketClient.getConfig().getPort()));
                Log.e(Tag.RemoteRepository,"--------------------------------------------");
                return;
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("---" + ctx.channel().remoteAddress() + " is state---");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        socketClient.doConnect();
    }

    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        System.out.println("---READER_IDLE---");
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        System.err.println("---WRITER_IDLE---");
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {

    }
}