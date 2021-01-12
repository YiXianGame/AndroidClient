package com.xianyu.yixian_client;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.xianyu.yixian_client.Model.Room.Entity.User;

/**
 * @ProjectName: YiXian
 * @Package: com.xianyu.yixian.Model
 * @ClassName: Core
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/11/25 10:32
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/11/25 10:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Core {
    public static MutableLiveData<User> liveUser = new MutableLiveData<User>();
    public static Gson gson;
}
