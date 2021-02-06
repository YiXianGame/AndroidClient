package com.xianyu.yixian_client.Model.Tcp;

import android.util.Log;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.RPC.ClientRequestModel;
import com.xianyu.yixian_client.Model.RPC.ClientResponseModel;
import com.xianyu.yixian_client.Model.RPC.RPCAdaptFactory;
import com.xianyu.yixian_client.Model.RPC.RPCAdaptProxy;
import com.xianyu.yixian_client.Model.RPC.ServerRequestModel;

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
            ClientRequestModel request = socketClient.tasks.get(Integer.parseInt((respond.Id)));
            if(request != null){
                socketClient.tasks.remove(Integer.parseInt((respond.Id)));
                request.setResult(respond.Result);
            }
            else {
                Log.e(Tag.RemoteRepository,"\n------------------未找到请求--------------------");
                Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.host,socketClient.port,respond));
                Log.e(Tag.RemoteRepository,"--------------------------------------------");
                return;
            }
        }
        else if(msg instanceof ServerRequestModel){
            ServerRequestModel request = (ServerRequestModel)msg;
            RPCAdaptProxy adapt;
            Method method;
            adapt = RPCAdaptFactory.services.get(new Triple<>(((ServerRequestModel) msg).Service, socketClient.host, socketClient.port));
            if(adapt != null){
                method = adapt.getMethods().get(request.MethodId);
                if(method!= null){
                    adapt.ConvertParams(request.MethodId,request.Params);
                    method.invoke(null,request.Params);
                }
                else {
                    Log.e(Tag.RemoteRepository,"\n------------------未找到该方法--------------------");
                    Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.host,socketClient.port,request));
                    Log.e(Tag.RemoteRepository,"--------------------------------------------");
                    return;
                }
            }
            else {
                Log.e(Tag.RemoteRepository,"\n------------------未找到该服务--------------------");
                Log.e(Tag.RemoteRepository,String.format("%s:%s::[客]\n%s",socketClient.host,socketClient.port,request));
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
        System.out.println("---" + ctx.channel().remoteAddress() + " is active---");
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