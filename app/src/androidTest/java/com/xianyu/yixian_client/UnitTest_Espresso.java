package com.xianyu.yixian_client;

import android.content.Context;


import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.xianyu.yixian_client.Frame.Login.Login_Activity;
import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@SmallTest
public class UnitTest_Espresso {
    @Inject
    DataBase_Room dataBase_room;

    public ActivityTestRule<Login_Activity> activityTestRule = new ActivityTestRule<Login_Activity>(Login_Activity.class);
    @Before
    public void Create_Database(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    }
    @Test
    public void Database(){
        User user = new User();
        user.setUserName("12312");
        user.setId(839336369);
        dataBase_room.userDao().insert(user);

    }
    @After
    public void closeDb() throws IOException {

    }
}