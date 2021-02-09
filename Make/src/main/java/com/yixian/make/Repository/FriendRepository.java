package com.yixian.make.Repository;

import android.annotation.SuppressLint;

import com.yixian.material.Entity.Friend;
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

public class FriendRepository {
    private LocalRepository local;
    private RemoteRepository remote;
    public FriendRepository(LocalRepository local,RemoteRepository remote){
        this.local = local;
        this.remote = remote;
    }

    public void delete(Friend... friends) {
        RxVoid(() -> local.db.friendDao().delete(friends));
    }

    public void update(Friend... friends) {
        RxVoid(() -> local.db.friendDao().update(friends));
    }

    public Single<List<Friend>> query(long user_id) {
        return local.db.friendDao().query(user_id);
    }

    public Single<List<User>> queryUsers(long user_id) {
        return local.db.friendDao().queryWithUsers(user_id);
    }
    public void insert(Friend... friends) {
        RxVoid(()->local.db.friendDao().insert(friends));
    }
    @SuppressLint("CheckResult")
    private <T,R> Single<R> Rx(Function0<R> functions){
        return Single.create(new SingleOnSubscribe<R>() {
        
            public void subscribe(@NonNull SingleEmitter<R> emitter) throws Exception {
                emitter.onSuccess(functions.invoke());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //返回值可能为空
    @SuppressLint("CheckResult")
    private <T,R> Maybe<R> RxNull(Function0<R> functions) {
        return Maybe.create(new MaybeOnSubscribe<R>() {
        
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
