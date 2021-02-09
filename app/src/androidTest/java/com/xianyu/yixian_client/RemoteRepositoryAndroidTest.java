package com.xianyu.yixian_client;

import androidx.test.filters.SmallTest;

import com.yixian.make.Repository.RemoteRepository;

import org.junit.Before;
import org.junit.Test;

@SmallTest
public class RemoteRepositoryAndroidTest {
    RemoteRepository repository;
    @Before
    public void init(){
        repository = new com.yixian.make.Repository.RemoteRepository();
    }
    @Test
    public void SendMessage(){

    }
}
