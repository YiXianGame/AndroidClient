package com.xianyu.yixian_client.Model.RPC.Server;

import android.util.Log;

import com.xianyu.yixian_client.Model.Log.Log.Tag;

public class CommandServer {
    public static void AddHp(int num)
    {
        Log.d(Tag.Debug,"客户端进行加血行为:" + num + "\n");
    }
}
