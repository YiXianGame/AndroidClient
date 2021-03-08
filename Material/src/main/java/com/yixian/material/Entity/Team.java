package com.yixian.material.Entity;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.yixian.material.Entity
 * @ClassName: Team
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/4 21:50
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/4 21:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Team {
    @Expose
    private String name;
    @Expose
    private HashMap<Long, Player> teammates = new HashMap<Long, Player>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Long, Player> getTeammates() {
        return teammates;
    }

    public void setTeammates(HashMap<Long, Player> teammates) {
        this.teammates = teammates;
    }
}
