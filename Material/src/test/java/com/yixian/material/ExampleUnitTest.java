package com.yixian.material;

import android.util.Log;
import android.util.Pair;

import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;
import com.yixian.material.Log.Log.Tag;

import org.junit.Test;

import java.io.Console;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        getActualType(new ArrayList<SkillCard>(){},0);
    }
    public  <T> void gene(ArrayList<SkillCard> skillCards){

    }
    public static void getActualType(Object o,int index) {
        Type clazz = o.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)clazz;
        if(clazz.equals(new ArrayList<SkillCard>() {
        }.getClass().getGenericSuperclass())){
            System.out.println("刷新了");
        }
        else System.out.println("不行");
    }
}