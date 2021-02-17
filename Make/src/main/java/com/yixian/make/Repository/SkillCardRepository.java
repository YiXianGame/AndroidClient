package com.yixian.make.Repository;

import android.annotation.SuppressLint;

import com.yixian.make.Repository.Base.LocalRepository;
import com.yixian.make.Repository.Base.RemoteRepository;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;
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

public class SkillCardRepository {
    private LocalRepository local;
    private RemoteRepository remote;
    public SkillCardRepository(LocalRepository local,RemoteRepository remote){
        this.local = local;
        this.remote = remote;
    }

    public void insert(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().insert(skillCards));
    }

    public void delete(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().delete(skillCards));
    }

    public void update(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().update(skillCards));
    }

    public Single<List<SkillCard>> queryByAuthor(long user_id) {
        return local.db.skillCardDao().queryByAuthorId(user_id);
    }

    public Maybe<SkillCard> queryById(long id) {
        return local.db.skillCardDao().queryById(id);
    }

    public Single<List<SkillCard>> queryAll() {
        return local.db.skillCardDao().queryAllSkillCards();
    }
    public Single<Boolean> sync(long timestamp){
        return Rx(() -> {
            //先在远程确认一下更新日期是否相同
            ArrayList<SkillCard> value = remote.skillCardRequest.Sync(timestamp);
            //不相同的话，将新数据插入到本地数据库
            if(value != null){
                local.db.skillCardDao().insert(value.toArray(new SkillCard[0]));
            }
            return true;
        });
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
