package com.xianyu.yixian_client;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.xianyu.yixian_client.Model.Core;
import com.xianyu.yixian_client.Model.Tcp.DateToLongSerializer;
import com.xianyu.yixian_client.Model.Tcp.LongToDateDeserializer;

import java.text.DateFormat;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client
 * @ClassName: XYApplication
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/20 12:32
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/20 12:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@HiltAndroidApp
public class XYApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Core.gson = new Gson().newBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(java.util.Date.class, new DateToLongSerializer()).setDateFormat(DateFormat.LONG)
                .registerTypeAdapter(java.util.Date.class, new LongToDateDeserializer()).setDateFormat(DateFormat.LONG)
                .create();
    }
}