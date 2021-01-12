package com.xianyu.yixian_client.Model.RPC;


import android.util.Log;
import android.util.Pair;

import com.xianyu.yixian_client.Model.Log.Log.Tag;
import com.xianyu.yixian_client.Model.Tcp.SocketClient;

import java.util.concurrent.ConcurrentHashMap;

public class RPCClientFactory {
        static ConcurrentHashMap<Pair<String,String>, SocketClient> clients = new ConcurrentHashMap<>();
        public static SocketClient GetClient(Pair<String,String> key){
            SocketClient socketClient = null;
            socketClient = clients.get(key);
            if(socketClient == null){
                socketClient = new SocketClient("192.168.43.238",28015);
                clients.put(key, socketClient);
                try {
                    socketClient.start();
                } catch (Exception e) {
                    Log.e(Tag.RemoteRepository,"TCP连接启动失败");
                    e.printStackTrace();
                }
            }
            socketClient.remain.getAndIncrement();
            return socketClient;
        }
        public static void Destory(Pair<String,String> key){
            SocketClient echoClient;
            echoClient = clients.get(key);
            if(echoClient != null){
                echoClient.remain.getAndDecrement();
                if(echoClient.remain.get() <= 0){
                    echoClient.disconnect();
                    clients.remove(key);
                }
            }
        }
}
