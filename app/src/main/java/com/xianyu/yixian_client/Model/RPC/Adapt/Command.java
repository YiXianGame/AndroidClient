package com.xianyu.yixian_client.Model.RPC.Adapt;

import android.util.Log;

import com.xianyu.yixian_client.Model.Log.Log.Tag;

public class Command {
    public static void AddHp(int num)
    {
        Log.d(Tag.Debug,"客户端进行加血行为:" + num + "\n");
    }
}
