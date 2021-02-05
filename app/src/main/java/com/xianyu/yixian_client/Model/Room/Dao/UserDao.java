package com.xianyu.yixian_client.Model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.xianyu.yixian_client.Model.Room.Convert.ActiveConvert;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;
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
    public void insert(User... user);

    @Query("UPDATE OR REPLACE user SET username=:username,nickname=:nickname,upgrade_num=:upgrade_num,create_num=:create_num," +
            "money=:money,personalSignature=:personalSignature,battleCount=:battleCount,exp=:exp,lv=:lv,title=:title,active=:active," +
            "kills=:kills,deaths=:deaths,registerDate=:registerDate,attribute_update=:attribute_update,skillCard_update=:skillCard_update," +
            "headImage_update=:headImage_update,cardGroup_update=:cardGroup_update WHERE id = :id")
    public void insertUserAttribute(long id, String username, String nickname, int upgrade_num,
                                    int create_num, long money, String personalSignature,
                                    int battleCount, long exp, int lv, String title, User.State active, int kills, int deaths, long registerDate,
                                    long attribute_update, long skillCard_update, long headImage_update, long cardGroup_update);
    @Query("UPDATE user SET password = :password WHERE id = :id")
    public void updateUserPassword(long id, String password);
    @Delete
    public void delete(User... user);

    @Query("SELECT * FROM user WHERE username = :username")
    public Single<User> queryByUserName(String username);
    @Query("SELECT * FROM user WHERE id = :id")
    public Single<User> queryById(long id);
    @Query("SELECT * FROM user WHERE id = :id")
    public User queryByIdSync(long id);
    @Query("SELECT * FROM user")
    public Single<List<User>> queryAll();
}
