package com.xianyu.yixian_client.Model.Repository;

import android.annotation.SuppressLint;

import com.xianyu.yixian_client.Model.Exception.UserException;
import com.xianyu.yixian_client.Model.Repository.Interface.IRepository;
import com.xianyu.yixian_client.Model.Room.DataBase_Room;
import com.xianyu.yixian_client.Model.Room.Entity.Config;
import com.xianyu.yixian_client.Model.Room.Entity.Friend;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.Model.Room.Entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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
public class Repository implements IRepository{
    private LocalRepository local;
    private RemoteRepository remote;
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public Repository(DataBase_Room db){
        local = new LocalRepository(db);
        remote = new RemoteRepository();
    }
    //这里是产生的Observable
    @Override
    public Single<Long> registerUser(User user) {
        return Rx(() -> remote.userRequest.RegisterUser(user.getUsername(),user.getNickname(),user.getPassword()));
    }
    @Override
    public Single<Long> loginUser(User user){
        return Rx(()->remote.userRequest.LoginUser(user.getId(),user.getUsername(),user.getPassword()));
    }

    @Override
    public void insertUser(User user) {
        RxVoid(()->local.db.userDao().insert(user));
    }

    @Override
    public Maybe<User> sync_UserAttribute(User user){
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

    @Override
    public void updateLocalAccount(User user) {
        RxVoid(()->local.db.userDao().updateAccount(user.getId(),user.getUsername(),user.getPassword()));
    }

    @Override
    public Maybe<User> queryLocalUser(long id) {
        return local.db.userDao().queryById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<User>> queryAllUsers() {
        return local.db.userDao().queryAll();
    }
    @Override
    public Maybe<User> queryUserByUserName(String userName) {
        return local.db.userDao().queryByUserName(userName);
    }
    @Override
    public Maybe<User> queryUserById(long id) {
        return local.db.userDao().queryById(id);
    }
    @Override
    public void insertFriend(Friend... friends) {
        RxVoid(()->local.db.friendDao().insert(friends));
    }
    @Override
    public void deleteFriend(Friend... friends) {
        RxVoid(() -> local.db.friendDao().delete(friends));
    }
    @Override
    public void updateFriend(Friend... friends) {
        RxVoid(() -> local.db.friendDao().update(friends));
    }
    @Override
    public Single<List<Friend>> queryFriends(long user_id) {
        return local.db.friendDao().query(user_id);
    }
    @Override
    public Single<List<User>> queryAllFriendUsers(long user_id) {
        return local.db.friendDao().queryWithUsers(user_id);
    }
    @Override
    public void insertSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().insert(skillCards));
    }
    @Override
    public void deleteSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().delete(skillCards));
    }
    @Override
    public void updateSkillCard(SkillCard... skillCards) {
        RxVoid(() -> local.db.skillCardDao().update(skillCards));
    }
    @Override
    public Single<List<SkillCard>> querySkillCardByAuthor(long user_id) {

        return local.db.skillCardDao().queryByAuthorId(user_id);
    }
    @Override
    public Maybe<SkillCard> querySkillCardById(long id) {
        return local.db.skillCardDao().queryById(id);
    }
    @Override
    public Single<List<SkillCard>> queryAllSkillCards() {
        return local.db.skillCardDao().queryAllSkillCards();
    }
    @Override
    public void insertConfig(Config... configs) {
        RxVoid(()->local.db.configDao().insert(configs));
    }
    @Override
    public void updateConfig(Config... configs) {
        local.db.configDao().update(configs);
    }
    @Override
    public void deleteConfig(Config... configs) {
        local.db.configDao().delete(configs);
    }
    @Override
    public Single<List<Config>> queryConfig(int start, int end) {
        return local.db.configDao().query(start,end);
    }
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
