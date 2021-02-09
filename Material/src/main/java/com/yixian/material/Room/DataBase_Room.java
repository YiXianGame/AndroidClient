package com.yixian.material.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yixian.material.Room.Dao.ConfigDao;
import com.yixian.material.Room.Dao.FriendDao;
import com.yixian.material.Room.Dao.SkillCardDao;
import com.yixian.material.Room.Dao.UserDao;
import com.yixian.material.Entity.Config;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.CardRepository;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model
 * @ClassName: DataBase_Room
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/19 22:35
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/19 22:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Database(entities = {User.class, SkillCard.class, Friend.class,CardRepository.class, Config.class},version = 1,exportSchema = false)
public abstract class DataBase_Room extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract SkillCardDao skillCardDao();
    public abstract FriendDao friendDao();
    public abstract ConfigDao configDao();
}
