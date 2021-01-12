package com.xianyu.yixian_client.Model.Repository;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Single;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.RemoteRepository
 * @ClassName: LocalRepository
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/19 23:01
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/19 23:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocalRepository implements ILocalRepository{
    private DataBase_Room db;

    public LocalRepository(DataBase_Room db){
        this.db = db;
    }

    public void insertUser(User user) {
        db.userDao().insert(user);
    }

    public Single<List<User>> queryUsers() {
        return db.userDao().query();
    }

    @Override
    public void deleteUser(User user) {
        db.userDao().delete(user);
    }

    @Override
    public void updateUser(User user) {
        db.userDao().update(user);
    }

    @Override
    public void clearAllUser(User user) {
       //**
    }

    @Override
    public void insertHistory(History... history) {
        db.historyDao().insert(history);
    }

    @Override
    public void deleteHistory(History... history) {
        db.historyDao().delete(history);
    }

    @Override
    public void updateHistory(History... history) {
        db.historyDao().update(history);
    }

    @Override
    public Single<List<History>> queryHistory(long user_id) {
        return db.historyDao().query(user_id);
    }


}
