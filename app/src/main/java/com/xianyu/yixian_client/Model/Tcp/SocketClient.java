package com.xianyu.yixian_client.Model.Tcp;

import android.util.Log;

import com.google.gson.Gson;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Enums;
import com.xianyu.yixian_client.Model.RPC.ClientRequestModel;

import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import static com.xianyu.yixian_client.Core.gson;

/**
 * @ProjectName: YiXian
 * @Package: com.xianyu.yixian.Model
 * @ClassName: EchoClient
 * @Description: TCP客户端
 * @Author: Jianxian
 * @CreateDate: 2020/11/16 20:17
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/11/16 20:17
 * @UpdateRemark: 类的第一次生成
 * @Version: 1.0
 */
public class SocketClient {
    public final String host;
    public final int port;
    private Channel channel;
    private Bootstrap bootstrap;
    public ConcurrentHashMap<Integer,ClientRequestModel> tasks = new ConcurrentHashMap<Integer, ClientRequestModel>();
    private Random random = new Random();
    public AtomicInteger remain = new AtomicInteger(0);
    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel.class)            //3
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new CustomDecoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new IdleStateHandler(0,0,5));
                            ch.pipeline().addLast(new CustomHeartbeatHandler(SocketClient.this));
                        }
                    });
            doConnect();
        }
        catch (Exception e){
            Log.e(Tag.RemoteRepository,"优雅的关闭EventLoopGroup");
            group.shutdownGracefully();
        }
    }
    public void doConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }
        ChannelFuture future = bootstrap.connect("127.0.0.1", 12345);
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();
                    System.out.println("Connect to server successfully!");
                } else {
                    System.out.println("Failed to connect to server, try connect after 10s");
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }
    public void disconnect() {
        channel.disconnect();
        channel.close();
    }
    public void SendVoid(ClientRequestModel request){
        if(channel!=null && channel.isActive()){
            channel.writeAndFlush(request);
        }
    }
    public void Send(ClientRequestModel request) throws UnsupportedEncodingException {
        if(channel!=null && channel.isActive()){
            int id = random.nextInt();
            while (tasks.containsKey(id)){
                id = random.nextInt();
            }
            request.Id = Integer.toString(id);
            tasks.put(id,request);
            channel.writeAndFlush(request);
        }
    }
}
