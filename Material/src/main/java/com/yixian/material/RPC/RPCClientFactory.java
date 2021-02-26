package com.yixian.material.RPC;


import android.util.Log;
import android.util.Pair;

import com.yixian.material.Log.Log.Tag;
import com.yixian.material.Tcp.SocketClient;

import java.util.concurrent.ConcurrentHashMap;

public class RPCClientFactory {
        static ConcurrentHashMap<Pair<String,String>, SocketClient> clients = new ConcurrentHashMap<>();
        public static SocketClient StartClient(String host,String port){
            Pair<String,String> key = new Pair<>(host,port);
            SocketClient socketClient = null;
            socketClient = clients.get(key);
            if(socketClient == null){
                socketClient = new SocketClient(key.first,key.second);
                clients.put(key, socketClient);
                try {
                    socketClient.start();
                } catch (Exception e) {
                    Log.e(Tag.RemoteRepository,"TCP连接启动失败");
                    e.printStackTrace();
                }
            }
            return socketClient;
        }
        public static SocketClient GetClient(Pair<String,String> key){
            return clients.get(key);
        }
        public static void Destory(Pair<String,String> key){
            SocketClient echoClient;
            echoClient = clients.get(key);
            if(echoClient != null){
                echoClient.disconnect();
            }
        }
}
