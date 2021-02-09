package com.xianyu.yixian_client;

import android.content.Context;


import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@SmallTest
public class UnitTest_Espresso {
    @Test
    public void addition_isCorrect() {
        ArrayList<User> list = new ArrayList<User>();
        gene(list, new ArrayList<SkillCard>());
    }
    public void gene(ArrayList<User> cls,ArrayList<SkillCard> c2){
        System.out.println(cls.getClass().getTypeName());
    }
}