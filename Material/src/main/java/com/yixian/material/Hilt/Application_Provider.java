package com.yixian.material.Hilt;

import android.content.Context;

import androidx.room.Room;

import com.yixian.material.Room.DataBase_Room;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Hilt
 * @ClassName: Application_Provider
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/20 17:35
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/20 17:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module
@InstallIn(SingletonComponent.class)
public class Application_Provider {
    //数据库 不需要Singleton，因为Factory是Singleton

}

