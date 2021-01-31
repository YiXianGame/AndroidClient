package com.xianyu.yixian_client.Model.Room.Entity;

import com.google.gson.annotations.Expose;

public class History {
    @Expose
    int kills = 0;
    @Expose
    boolean result = false;
    @Expose
    long time = 0;
    @Expose
    int death = 0;
    
    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public long getTime(){ return time;}

    public  int getDeath(){ return death;}

    public void setTime(long time) {
        this.time = time;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public History(int kills, boolean result) {
        this.kills = kills;
        this.result = result;
    }
}
