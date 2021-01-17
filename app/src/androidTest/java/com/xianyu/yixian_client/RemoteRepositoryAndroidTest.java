package com.xianyu.yixian_client;

import androidx.test.filters.SmallTest;

import com.xianyu.yixian_client.Model.Repository.RemoteRepository;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

@SmallTest
public class RemoteRepositoryAndroidTest {
    RemoteRepository repository;
    @Before
    public void init(){
        repository = new com.xianyu.yixian_client.Model.Repository.RemoteRepository();
    }
    @Test
    public void SendMessage(){
        Observable.create((ObservableOnSubscribe<Void>) emitter -> repository.userDao.hello("你好")).subscribeOn(Schedulers.io()).subscribe();
    }
}
