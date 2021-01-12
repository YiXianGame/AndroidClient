package com.xianyu.yixian_client.Model.Tcp;

import com.xianyu.yixian_client.Model.RPC.ClientRequestModel;
import com.xianyu.yixian_client.Model.RPC.ClientResponseModel;
import com.xianyu.yixian_client.Model.RPC.RPCAdaptFactory;
import com.xianyu.yixian_client.Model.RPC.RPCAdaptProxy;
import com.xianyu.yixian_client.Model.RPC.ServerRequestModel;

import java.lang.reflect.Method;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import kotlin.Triple;

/**
 * @author xiongyongshun
 * @version 1.0
 * @email yongshun1228@gmail.com
 * @created 16/9/18 13:02
 */
public class CustomHeartbeatHandler extends ChannelInboundHandlerAdapter{

    private int heartbeatCount = 0;
    private SocketClient socketClient;
    public CustomHeartbeatHandler(SocketClient socketClient){
        this.socketClient = socketClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ClientResponseModel){
           ClientRequestModel request = socketClient.tasks.get(((ClientResponseModel) msg).Id);
           request.setResult(((ClientResponseModel) msg).Result);
        }
        else if(msg instanceof ServerRequestModel){
            ServerRequestModel request = (ServerRequestModel)msg;
            RPCAdaptProxy adapt = RPCAdaptFactory.services.get(new Triple<>(((ServerRequestModel) msg).Service,socketClient.host,socketClient.port));
            if(adapt != null){
                Method method = adapt.methods.get(request.MethodId);
                if(method != null){
                    method.invoke(null,request.Params);
                }
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
        System.err.println("---" + ctx.channel().remoteAddress() + " is active---");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        socketClient.doConnect();
    }

    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        System.err.println("---READER_IDLE---");
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        System.err.println("---WRITER_IDLE---");
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        System.err.println("---ALL_IDLE---");
    }
}