package com.yixian.make.Repository;

import android.annotation.SuppressLint;

import com.yixian.material.Entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.functions.Function0;

public class UserRepository {
    private LocalRepository local;
    private RemoteRepository remote;
    public UserRepository(LocalRepository local,RemoteRepository remote){
        this.local = local;
        this.remote = remote;
    }

    public Single<Long> register(User user) {
        return Single.create(new SingleOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Long> emitter) {
                long result = remote.userRequest.RegisterUser(user.getUsername(), user.getNickname(), user.getPassword());
                emitter.onSuccess(result);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Long> login(User user){
        return Rx(()->remote.userRequest.LoginUser(user.getId(), user.getUsername(), user.getPassword()));
    }


    public void local_insert(User user) {
        RxVoid(()->local.db.userDao().insert(user));
    }


    public Maybe<User> syncAttribute(User user){
        return RxNull(() -> {
            //先在远程确认一下更新日期是否相同
            User value = remote.userRequest.Sync_UserAttribute(user.getAttribute_update());
            //不相同的话，将新数据插入到本地数据库
            if(value != null){
                local.updateUserAttribute(value);
            }
            else {
                //相同的话从本地数据库取数据
                value = local.db.userDao().queryByIdSync(user.getId());
                //本地数据库的数据找不到了，从远程取一下.
                if(value == null){
                    value = remote.userRequest.Query_UserAttributeById(user.getId());
                    if(value != null){
                        local.db.userDao().insert(value);
                    }
                }
            }
            return value;
        });
    }

    public void local_update(User user) {
        RxVoid(()->local.db.userDao().updateAccount(user.getId(), user.getUsername(), user.getPassword()));
    }

    public Maybe<User> local_query(long id) {
        return local.db.userDao().queryById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<User>> local_queryAll() {
        return local.db.userDao().queryAll();
    }

    public Maybe<User> local_queryByUserName(String userName) {
        return local.db.userDao().queryByUserName(userName);
    }

    public Maybe<User> local_queryById(long id) {
        return local.db.userDao().queryById(id);
    }


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
