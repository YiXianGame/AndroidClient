package com.yixian.make.Model;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.yixian.make.Repository.ConfigRepository;
import com.yixian.make.Repository.FriendRepository;
import com.yixian.make.Repository.LocalRepository;
import com.yixian.make.Repository.RemoteRepository;
import com.yixian.make.Repository.SkillCardRepository;
import com.yixian.make.Repository.UserRepository;
import com.yixian.material.Room.DataBase_Room;
import com.yixian.material.Hilt.Application_Provider;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.functions.Function0;
/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Model.repository
 * @ClassName: RepositoryFactory
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/20 16:44
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/20 16:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Singleton
public class Repository{
    private LocalRepository local;
    private RemoteRepository remote;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public UserRepository userRepository;
    public SkillCardRepository skillCardRepository;
    public ConfigRepository configRepository;
    public FriendRepository friendRepository;
    @Inject
    public Repository(@ApplicationContext Context context){
        DataBase_Room db = Room.databaseBuilder(context, DataBase_Room.class,"db").build();
        local = new LocalRepository(db);
        remote = new RemoteRepository();
        userRepository = new UserRepository(local,remote);
        skillCardRepository = new SkillCardRepository(local,remote);
        configRepository = new ConfigRepository(local,remote);
        friendRepository = new FriendRepository(local,remote);
    }
    //这里是产生的Observable


    //返回值不为空
    @SuppressLint("CheckResult")
    private <T,R> Single<R> Rx(Function0<R> functions){
        return Single.create(new SingleOnSubscribe<R>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<R> emitter) throws Exception {
                emitter.onSuccess(functions.invoke());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //返回值可能为空
    @SuppressLint("CheckResult")
    private <T,R> Maybe<R> RxNull(Function0<R> functions) {
        return Maybe.create(new MaybeOnSubscribe<R>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<R> emitter) throws Exception {
                try{
                    R result = functions.invoke();
                    if(result!=null){
                        emitter.onSuccess(result);
                    }
                    else emitter.onComplete();
                }
                catch (Exception e){
                    emitter.onError(e);
                    throw e;
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //无返回值
    @SuppressLint("CheckResult")
    private <T,R> void RxVoid(Action action) {
        Single.create((SingleOnSubscribe<Integer>) emitter -> {
            action.run();
            emitter.onSuccess(1);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
