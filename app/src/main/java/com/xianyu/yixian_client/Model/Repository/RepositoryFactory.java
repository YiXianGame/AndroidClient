package com.xianyu.yixian_client.Model.Repository;

import android.annotation.SuppressLint;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
public class RepositoryFactory{
    private LocalRepository local;
    private RemoteRepository remote;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public RepositoryFactory(DataBase_Room db){
        local = new LocalRepository(db);
        remote = new RemoteRepository();
    }

    public void insertUser(User user) {
        RxNoneOne(arg -> local.insertUser(arg), user);
    }

    public Single<List<User>> queryUsers() {
        return local.queryUsers();
    }


    public void deleteUser(User arg) {
        RxNoneOne(user -> local.deleteUser(user), arg);
    }

    public void clearAllUser(User arg) {
        RxNoneOne(user -> local.clearAllUser(user), arg);
    }

    public void updateUser(User user) {
        RxNoneOne(arg -> local.insertUser(arg), user);
    }

    public void insertHistory(History... history) {
        RxNoneOne(arg -> local.insertHistory(history), history);
    }

    public void deleteHistory(History... history) {
        RxNoneOne(arg -> local.deleteHistory(history), history);
    }

    public void updateHistory(History... history) {
        RxNoneOne(arg -> local.updateHistory(history), history);
    }

    public Single<List<History>> queryHistory(long user_id) {
        return local.queryHistory(user_id);
    }
    //RxJava2的异步方法封装
    @SuppressLint("CheckResult")
    public <T> void RxNoneOne(Consumer<T> functions,T arg){
        Observable.create(new ObservableOnSubscribe<T>() {
        
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(arg);
                emitter.onComplete();
            }
        }).observeOn(Schedulers.io())
                .subscribe(new Consumer<T>() {
                
                    public void accept(T arg) throws Exception {
                            functions.accept(arg);
                    }
                });
    }
}
