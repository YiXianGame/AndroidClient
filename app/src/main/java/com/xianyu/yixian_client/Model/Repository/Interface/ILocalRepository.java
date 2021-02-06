package com.xianyu.yixian_client.Model.Repository.Interface;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.xianyu.yixian_client.Model.Room.Entity.Config;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.Single;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.repository
 * @ClassName: ILocalRepository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/24 3:28
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/24 3:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ILocalRepository {
    void updateUserAttribute(User user);

}
