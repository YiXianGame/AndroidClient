package com.yixian.material.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yixian.material.Entity.SkillCard;

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
public interface SkillCardDao {
    @Insert
    public void insert(SkillCard... skillCards);

    @Update
    public void update(SkillCard... skillCards);

    @Delete
    public void delete(SkillCard... skillCards);

    @Query("SELECT * FROM skillcard WHERE authorId = :user_id")
    public Single<List<SkillCard>> queryByAuthorId(long user_id);

    @Query("SELECT * FROM skillcard WHERE id = :id")
    public Maybe<SkillCard> queryById(long id);

    @Query("SELECT * FROM skillcard")
    public Single<List<SkillCard>> queryAllSkillCards();
}
