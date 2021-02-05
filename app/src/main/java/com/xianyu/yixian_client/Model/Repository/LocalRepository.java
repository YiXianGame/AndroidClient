package com.xianyu.yixian_client.Model.Repository;

import com.xianyu.yixian_client.Model.Repository.Interface.ILocalRepository;
import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.User;


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
public class LocalRepository implements ILocalRepository {
    
    public DataBase_Room db;

    public LocalRepository(DataBase_Room db){
        this.db = db;
    }

    @Override
    public void insertOrReplaceUserAttribute(User user) {
        db.userDao().insertUserAttribute(user.getId(),user.getUsername(),user.getNickname(),user.getUpgrade_num(),
                user.getCreate_num(),user.getMoney(),user.getPersonalSignature(),user.getBattleCount(),
                user.getExp(),user.getLv(),user.getTitle(),user.getActive(),user.getKills(),user.getDeaths(),user.getRegisterDate(),
                user.getAttribute_update(),user.getSkillCard_update(),user.getHeadImage_update(),user.getCardGroup_update());
    }
}
