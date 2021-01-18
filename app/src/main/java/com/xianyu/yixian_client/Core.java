package com.xianyu.yixian_client;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.ArrayList;

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
    public static MutableLiveData<ArrayList<SkillCard>> liveSkillcards = new MutableLiveData<>();
    public static Gson gson = new Gson().newBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).excludeFieldsWithoutExposeAnnotation().create();;
}
