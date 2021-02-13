package com.xianyu.yixian_client;

import android.app.Application;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.yixian.make.Core;
import com.yixian.make.Model.Repository;
import com.yixian.make.Repository.ConfigRepository;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.ArrayList;

import javax.inject.Inject;

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
    @Inject
    Repository repository;
    @GlideModule
    public final class MyGlideModule extends AppGlideModule {}
    @Override
    public void onCreate() {
        super.onCreate();
        Core.repository = repository;
    }
}
