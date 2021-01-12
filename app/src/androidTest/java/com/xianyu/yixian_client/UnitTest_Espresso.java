package com.xianyu.yixian_client;

import android.content.Context;


import androidx.lifecycle.Observer;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.xianyu.yixian_client.Login.Login;
import com.xianyu.yixian_client.Model.Room.DataBase_Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
    @Rule
    public ActivityTestRule<Login> activityTestRule = new ActivityTestRule<Login>(Login.class);
    @Before
    public void Create_Database(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    }
    @Test
    public void Database(){
        User user = new User();
        user.setUserName("12312");
        user.setQQ(839336369);
        dataBase_room.userDao().insert(user);

    }
    @After
    public void closeDb() throws IOException {

    }
}