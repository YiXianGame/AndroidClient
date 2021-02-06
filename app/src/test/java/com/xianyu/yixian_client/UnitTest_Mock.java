package com.xianyu.yixian_client;


import android.content.Context;


import androidx.appcompat.app.AppCompatActivity;

import com.xianyu.yixian_client.Model.RPC.RPCRequestProxy;
import com.xianyu.yixian_client.Model.RPC.Request.UserRequest;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTest_Mock {
    @Mock
    Context mockContext;
    @Mock
    AppCompatActivity activity;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void Create_Database(){

    }
    @Test
    public void Database(){

        List mockedList = mock(List.class);


        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void proxy(){
        RPCRequestProxy proxy = new RPCRequestProxy();
        Class<?>[] d =  UserRequest.class.getInterfaces();
        com.xianyu.yixian_client.Model.RPC.Request.UserRequest user = (com.xianyu.yixian_client.Model.RPC.Request.UserRequest) Proxy.newProxyInstance(UserRequest.class.getClassLoader(), d, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                return null;
            }
        });
    }


    @After
    public void closeDb() throws IOException {

    }
}