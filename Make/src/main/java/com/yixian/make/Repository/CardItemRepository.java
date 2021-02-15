package com.yixian.make.Repository;

import android.annotation.SuppressLint;

import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.SkillCard;

import java.util.ArrayList;

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

public class CardItemRepository {
    private LocalRepository local;
    private RemoteRepository remote;
    public CardItemRepository(LocalRepository local,RemoteRepository remote){
        this.local = local;
        this.remote = remote;
    }
    public Maybe<ArrayList<SkillCard>> QuerySkillCardsByUserId(long id){
        return RxNull(()->{
            ArrayList<SkillCard> skillCards = new ArrayList<>();
            ArrayList<CardItem> value = new ArrayList<>(local.db.cardRepositoryDao().queryByIdSync(id));
            ArrayList<Long> none = new ArrayList<>();
            for(CardItem item : value){
                SkillCard skillCard = local.db.skillCardDao().queryByIdSync(item.getItemId());
                if(skillCard == null)none.add(item.getItemId());
                else skillCards.add(skillCard);
            }
            if(none.size()>0){
                ArrayList<SkillCard> syncSkillCards = remote.skillCardRequest.Query(none);
                if(syncSkillCards!=null){
                    local.db.skillCardDao().insert(skillCards.toArray(new SkillCard[0]));
                    skillCards.addAll(syncSkillCards);
                }
                else return null;
            }
            return skillCards;
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
