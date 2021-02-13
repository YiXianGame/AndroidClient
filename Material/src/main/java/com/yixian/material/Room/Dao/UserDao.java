package com.yixian.material.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.yixian.material.Entity.User;
import com.yixian.material.Room.Convert.ActiveConvert;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;


/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.Room.Dao
 * @ClassName: UserDao
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/19 22:38
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/19 22:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);
    @Query("UPDATE user SET username=:username,password=:password WHERE id=:id")
    void updateAccount(long id,String username,String password);
    @Query("UPDATE user SET skillCard_update=:timestamp WHERE id=:id")
    void updateSKillCardUpdate(long id,long timestamp);
    @TypeConverters(ActiveConvert.class)
    @Query("UPDATE user SET username=:username,nickname=:nickname,upgrade_num=:upgrade_num,create_num=:create_num," +
            "money=:money,personalSignature=:personalSignature,battleCount=:battleCount,exp=:exp,lv=:lv,title=:title,active=:active," +
            "kills=:kills,deaths=:deaths,registerDate=:registerDate,attribute_update=:attribute_update " +
            "WHERE id = :id")
    void insertUserAttribute(long id, String username, String nickname, int upgrade_num,
                             int create_num, long money, String personalSignature,
                             int battleCount, long exp, int lv, String title, User.State active, int kills, int deaths, long registerDate,
                             long attribute_update);
    @Query("UPDATE user SET password = :password WHERE id = :id")
    void updateUserPassword(long id, String password);
    @Delete
    void delete(User... user);

    @Query("SELECT * FROM user WHERE username = :username")
    Maybe<User> queryByUserName(String username);
    @Query("SELECT * FROM user WHERE id = :id")
    Maybe<User> queryById(long id);
    @Query("SELECT * FROM user WHERE id = :id")
    User queryByIdSync(long id);
    @Query("SELECT * FROM user")
    Single<List<User>> queryAll();
}
