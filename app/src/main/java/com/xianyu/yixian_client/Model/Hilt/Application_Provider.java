package com.xianyu.yixian_client.Model.Hilt;

import android.content.Context;

import androidx.room.Room;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;

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
    @Provides
    public DataBase_Room provideDB_Room(@ApplicationContext Context context){
        return Room.inMemoryDatabaseBuilder(context, DataBase_Room.class).build();
    }

    /* 因为Hilt还不能很方便的支持任一类注入子段,暂时先不注入了.
    //ReceiveEvent注入

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Information_ReceiveEvent {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Game_ReceiveEvent {}

    @EntryPoint
    @InstallIn(SingletonComponent.class)
    interface ReceiveEventProviderEntryPoint{
        public ReceiveEvent ReceiveEventProvide();
    }
    @Provides    //目前Hilt,想要ReceiveEvent拓展两种注入的时候，必须先实现基本构造
    public ReceiveEvent ReceiveEventProvide(){
        return new ReceiveEvent();
    }
    @Provides
    @Information_ReceiveEvent
    public ReceiveEvent InformationProvide(){
        return new ReceiveEvent();
    }
    @Provides
    @Game_ReceiveEvent
    public ReceiveEvent GameProvide(){
        return new ReceiveEvent();
    }


    这里是将来注入的子段代码
    @Inject
    @Application_Provider.Information_ReceiveEvent
    public ReceiveEvent information_ReceiveEvent;
    @Inject
    @Application_Provider.Game_ReceiveEvent
    public ReceiveEvent gameReceiveEvent;
    */

}

