package com.xianyu.yixian_client.Model.Room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.xianyu.yixian_client.Model.Room.Dao.HistoryDao;
import com.xianyu.yixian_client.Model.Room.Dao.SkillCardDao;
import com.xianyu.yixian_client.Model.Room.Dao.UserDao;
import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.CardGroup;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.Repository;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;


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
@Database(entities = {User.class,SkillCard.class, Buff.class, CardGroup.class, Friend.class , Repository.class, History.class},version = 2,exportSchema = false)
public abstract class DataBase_Room extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract SkillCardDao skillCardDao();
    public abstract HistoryDao historyDao();
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

}
