package com.yixian.make;

import androidx.lifecycle.MutableLiveData;

import com.yixian.make.Model.Repository;
import com.yixian.material.Entity.Config;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import java.util.ArrayList;

public class Core {
    public static MutableLiveData<Config> liveConfig = new MutableLiveData<>();
    public static MutableLiveData<User> liveUser = new MutableLiveData<User>();
    public static MutableLiveData<ArrayList<SkillCard>> liveSkillcards = new MutableLiveData<>();
    public static Repository repository;
}
