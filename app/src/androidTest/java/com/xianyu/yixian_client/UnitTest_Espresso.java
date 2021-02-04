package com.xianyu.yixian_client;

import android.content.Context;
import android.widget.Button;


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
    @Before
    public void Create_Database(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
    @Test
    public void Database(){

    }
    @After
    public void closeDb() throws IOException {

    }
}