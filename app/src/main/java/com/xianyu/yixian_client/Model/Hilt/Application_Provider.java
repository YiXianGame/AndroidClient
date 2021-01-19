package com.xianyu.yixian_client.Model.Hilt;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.Model.Room.DataBase_Room;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
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
    @Provides
    public DataBase_Room provideDB_Room(@ApplicationContext Context context){
        return Room.inMemoryDatabaseBuilder(context, DataBase_Room.class).build();
    }

}

