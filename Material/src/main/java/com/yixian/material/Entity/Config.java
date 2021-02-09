package com.yixian.material.Entity;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
@Entity(tableName = "config")
public class Config {
    @PrimaryKey
    private long id;
    private long skillCardUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSkillCardUpdate() {
        return skillCardUpdate;
    }

    public void setSkillCardUpdate(long skillCardUpdate) {
        this.skillCardUpdate = skillCardUpdate;
    }

}
