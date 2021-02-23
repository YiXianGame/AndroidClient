package com.yixian.make.Event.ReadyEvent.Args;

import com.yixian.material.Entity.User;

import java.util.ArrayList;

public class MatchSuccessArgs {
    private ArrayList<User> group_1;
    private ArrayList<User> group_2;
    private int idx;
    private String hostname;
    private String port;
    private String hash;

    public MatchSuccessArgs(ArrayList<User> group_1, ArrayList<User> group_2, int idx, String hostname, String port, String hash) {
        this.group_1 = group_1;
        this.group_2 = group_2;
        this.idx = idx;
        this.hostname = hostname;
        this.port = port;
        this.hash = hash;
    }

    public ArrayList<User> getGroup_1() {
        return group_1;
    }

    public void setGroup_1(ArrayList<User> group_1) {
        this.group_1 = group_1;
    }

    public ArrayList<User> getGroup_2() {
        return group_2;
    }

    public void setGroup_2(ArrayList<User> group_2) {
        this.group_2 = group_2;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
