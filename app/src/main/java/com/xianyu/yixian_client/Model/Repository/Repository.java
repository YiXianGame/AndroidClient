package com.xianyu.yixian_client.Model.Repository;

import android.annotation.SuppressLint;

import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    @Inject
    public Repository(DataBase_Room db){
        local = new LocalRepository(db);
        remote = new RemoteRepository();
    }
    //这里是产生的Observable
    public Single<Long> registerUser(User user,String password) {
        return Rx(() -> remote.userRequest.RegisterUser(user.getUsername(),user.getNickname(),password));
    }

    public Single<Long> loginUser(User user,String password){
        return Rx(()->remote.userRequest.LoginUser(user.getUsername(),password));
    }
    public Single<User> update_UserAttribute(User user){
        return Rx(()->{
            //先在远程确认一下更新日期是否相同
            User value = remote.userRequest.Sync_UserAttribute(user.getId(),user.getAttribute_update());
            //不相同的话，将新数据插入到本地数据库
            if(value != null){
                local.insertOrReplaceUserAttribute(user);
            }
            else {
                //相同的话从本地数据库取数据
                value = local.queryByIdSync(user.getId());
                //本地数据库的数据找不到了，从远程取一下.
                if(value == null){
                    value = remote.userRequest.QueryUserById(user.getId());
                }
            }
            return value;
        });
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
        RxVoid(()->local.insertFriend(friends));
    }

    public void deleteFriend(Friend... friends) {
        RxVoid(() -> local.deleteFriend(friends));
    }

    public void updateFriend(Friend... friends) {
        RxVoid(() -> local.updateFriend(friends));
    }

    public Single<List<Friend>> queryFriends(long user_id) {
        return local.queryFriends(user_id);
    }

    public void insertSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.insertSkillCard(skillCards));
    }

    public void deleteSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.deleteSkillCard(skillCards));
    }

    public void updateSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.updateSkillCard(skillCards));
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
    //RxJava2的异步方法封装
    @SuppressLint("CheckResult")
    private <T,R> void RxVoid(Action action){
        Single.create((SingleOnSubscribe<Integer>) emitter -> {
            action.run();
            emitter.onSuccess(1);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
