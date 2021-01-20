package com.xianyu.yixian_client.Model.Repository;

import android.annotation.SuppressLint;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
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
public class Repository{
    private LocalRepository local;
    private RemoteRepository remote;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public Repository(DataBase_Room db){
        local = new LocalRepository(db);
        remote = new RemoteRepository();
    }

    public void insertUser(User user) {
        RxNoneOne(arg -> local.insertUser(arg), user);
    }

    public Single<List<User>> queryAllUsers() {
        return local.queryAllUsers();
    }

    public void deleteUser(User arg) {
        RxNoneOne(user -> local.deleteUser(user), arg);
    }

    public void updateUser(User user) {
        RxNoneOne(arg -> local.insertUser(arg), user);
    }

    public Single<User> queryUserByUserName(String userName) {
        return local.queryUserByUserName(userName);
    }

    public Single<User> queryUserById(long id) {
        return local.queryUserById(id);
    }

    public void insertFriend(Friend... friends) {
        RxNoneOne(arg -> local.insertFriend(friends), friends);
    }

    public void deleteFriend(Friend... friends) {
        RxNoneOne(arg -> local.deleteFriend(friends), friends);
    }

    public void updateFriend(Friend... friends) {
        RxNoneOne(arg -> local.updateFriend(friends), friends);
    }

    public Single<List<Friend>> queryFriends(long user_id) {
        return local.queryFriends(user_id);
    }

    public void insertSkillCard(SkillCard... skillCards) {
        RxNoneOne(arg -> local.insertSkillCard(skillCards), skillCards);
    }

    public void deleteSkillCard(SkillCard... skillCards) {
        RxNoneOne(arg -> local.deleteSkillCard(skillCards), skillCards);
    }

    public void updateSkillCard(SkillCard... skillCards) {
        RxNoneOne(arg -> local.updateSkillCard(skillCards), skillCards);
    }

    public Single<List<SkillCard>> querySkillCardByAuthor(long user_id) {

        return local.querySkillCardByAuthor(user_id);
    }

    public Single<SkillCard> querySkillCardById(long id) {
        return local.querySkillCardById(id);
    }

    public Single<List<SkillCard>> queryAllSkillCards() {
        return local.queryAllSkillCards();
    }

    public Single<List<User>> queryAllFriendUsers(long user_id) {
        return local.queryAllFriendUsers(user_id);
    }
    //RxJava2的异步方法封装
    @SuppressLint("CheckResult")
    public <T> void RxNoneOne(Consumer<T> functions,T arg){
        Observable.create((ObservableOnSubscribe<T>) emitter -> {
            emitter.onNext(arg);
            emitter.onComplete();
        }).observeOn(Schedulers.io())
                .subscribe(arg1 -> functions.accept(arg1));
    }
}
