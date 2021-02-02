package com.xianyu.yixian_client.Model.Tcp;

import android.util.Log;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.RPC.ClientRequestModel;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

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
    public final String port;
    private Channel channel;
    private Bootstrap bootstrap;
    public ConcurrentHashMap<Integer,ClientRequestModel> tasks = new ConcurrentHashMap<>();
    private Random random = new Random();
    public AtomicInteger remain = new AtomicInteger(0);
    public SocketClient(String host, String port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap();                //1
            bootstrap.group(group)                                //2
                    .option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(1024))
                    .channel(NioSocketChannel.class)            //3
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new CustomDecoder());

                            ch.pipeline().addLast(new IdleStateHandler(0,0,5));
                            ch.pipeline().addLast(new CustomHeartbeatHandler(SocketClient.this));

                            ch.pipeline().addLast(new CustomEncoder());
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
        ChannelFuture future = bootstrap.connect(host, Integer.parseInt(port));
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
    public void Send(ClientRequestModel request) {
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
