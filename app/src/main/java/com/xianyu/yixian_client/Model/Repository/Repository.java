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
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
    @Inject
    public Repository(DataBase_Room db){
        local = new LocalRepository(db);
        remote = new RemoteRepository();
    }
    //这里是产生的Observable
    public Single<Long> registerUser(User user,String password) {
        return Rx(() -> remote.userRequest.RegisterUser(user.getUserName(),user.getNickName(),password));
    }

    public Single<Long> loginUser(User user,String password){
        return Rx(()->remote.userRequest.LoginUser(user.getUserName(),password));
    }
    public void update_UserAttribute(User user){

    }
    public Single<List<User>> queryAllUsers() {
        return local.queryAllUsers();
    }

    public Single<User> queryUserByUserName(String userName) {
        return local.queryUserByUserName(userName);
    }

    public Single<User> queryUserById(long id) {
        return local.queryUserById(id);
    }

    public void insertFriend(Friend... friends) {
        Rx(arg -> local.insertFriend(friends));
    }

    public void deleteFriend(Friend... friends) {
        Rx(arg -> local.deleteFriend(friends));
    }

    public void updateFriend(Friend... friends) {
        Rx(arg -> local.updateFriend(friends));
    }

    public Single<List<Friend>> queryFriends(long user_id) {
        return local.queryFriends(user_id);
    }

    public void insertSkillCard(SkillCard... skillCards) {
        Rx(arg -> local.insertSkillCard(skillCards));
    }

    public void deleteSkillCard(SkillCard... skillCards) {
        Rx(arg -> local.deleteSkillCard(skillCards));
    }

    public void updateSkillCard(SkillCard... skillCards) {
        Rx(arg -> local.updateSkillCard(skillCards));
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
    private <T,R> Single<R> Rx(Function0<R> functions){
        return Single.create((SingleOnSubscribe<R>) emitter -> {
            emitter.onSuccess(functions.invoke());
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
