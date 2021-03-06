package com.yixian.make;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.yixian.make.Event.ReadyEvent.ReadyEvent;
import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.Config;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Core {
    public static MutableLiveData<Config> liveConfig = new MutableLiveData<>();
    public static MutableLiveData<User> liveUser = new MutableLiveData<User>();
    public static MutableLiveData<ArrayList<User>> liveSquad = new MutableLiveData<>();
    public static MutableLiveData<ArrayList<User>> liveFriends = new MutableLiveData<ArrayList<User>>();
    public static MutableLiveData<HashMap<Long,SkillCard>> liveSkillcards = new MutableLiveData<>();
    public static Repository repository;
    public static Pair<String,String> userServer = new Pair<>("10.163.193.51","28015");
    public static Pair<String,String> playerServer;
}
