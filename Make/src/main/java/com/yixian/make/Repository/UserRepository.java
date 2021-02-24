package com.yixian.make.Repository;

import android.annotation.SuppressLint;

import com.google.gson.reflect.TypeToken;
import com.yixian.make.Repository.Base.LocalRepository;
import com.yixian.make.Repository.Base.RemoteRepository;
import com.yixian.material.Entity.CardGroup;
import com.yixian.material.Entity.CardItem;
import com.yixian.material.Entity.Friend;
import com.yixian.material.Entity.SkillCard;
import com.yixian.material.Entity.User;
import com.yixian.material.Utils.Utils;

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
    public void updateCardGroups(User user){
        RxVoid(()->{
            long timestamp = remote.userRequest.Update_CardGroups(user);
            if(timestamp != -1){
                user.setCardGroups_update(timestamp);
                local.db.userDao().updateCardGroups(user.getId(),Utils.gson.toJson(user.getCardGroups(),new TypeToken<ArrayList<CardGroup>>(){}.getType()),timestamp);
            }
        });
    }

    public void local_insert(User user) {
        RxVoid(()->local.db.userDao().insert(user));
    }

    public Single<ArrayList<User>> queryUsers(ArrayList<Long> users) {
        return Rx(()->queryUsersSync(users));
    }

    public Maybe<User> syncAttribute(User user){
        return RxNull(() -> {
            //先在远程确认一下更新日期是否相同
            User value = remote.userRequest.Sync_Attribute(user.getAttribute_update());
            //不相同的话，将新数据插入到本地数据库
            if(value != null){
                local.updateUserAttribute(value);
            }
            //相同的话从本地数据库取数据
            value = local.db.userDao().queryByIdSync(user.getId());
            return value;
        });
    }
    public ArrayList<User> queryUsersSync(ArrayList<Long> users){
        ArrayList<User> result = new ArrayList<>();
        ArrayList<User> sync = new ArrayList<User>(){};
        for(Long item : users){
            User queryUser = local.db.userDao().queryByIdSync(item);
            User temp = new User();
            temp.setId(item);
            if(queryUser ==null){//本地没有就存起来，待会一块从远程查找
                temp.setAttribute_update(-1);
            }
            else {
                temp.setAttribute_update(queryUser.getAttribute_update());
            }
            sync.add(temp);
        }
        sync = remote.userRequest.Sync_Attribute(sync);
        for(User item : sync){
            if(item!=null){
                local.db.userDao().insertSync(item);
            }
        }
        for(Long item : users){
            User user = local.db.userDao().queryByIdSync(item);
            result.add(user);
        }
        return result;
    }
    public Single<ArrayList<User>> syncFriend(User user){
        return Rx(() -> {
            //先在远程确认一下更新日期是否相同
            List<Friend> value = remote.userRequest.Sync_Friend(user.getFriend_update());
            ArrayList<Long> usersId = new ArrayList<>();
            boolean is_remote = true;
            if(value == null){
                //相同的话从本地数据库取数据
                is_remote = false;
                value = local.db.friendDao().query_Sync(user.getId());
            }
            for(Friend item : value){
                if(item.getUser_1()!=user.getId())usersId.add(item.getUser_1());
                else usersId.add(item.getUser_2());
            }
            ArrayList<User> friends = this.queryUsersSync(usersId);
            if(is_remote)local.db.friendDao().insert(value.toArray(new Friend[0]));
            return friends;
        });
    }
    public Single<User> syncCardGroups(User user){
        return Rx(() -> {
            ArrayList<User> users = new ArrayList<User>(){};
            users.add(user);
            //先在远程确认一下更新日期是否相同
            ArrayList<User> value = remote.userRequest.Sync_CardGroups(users);
            for (User item:value) {
                if(item!=null){
                    user.setCardGroups_update(item.getCardGroups_update());
                    user.setCardGroups(item.getCardGroups());
                    local.db.userDao().updateCardGroups(item.getId(), Utils.gson.toJson(item.getCardGroups()),item.getCardGroups_update());
                }
            }
            return user;
        });
    }
    public Maybe<ArrayList<SkillCard>> syncUserSkillCard(User user){
        return RxNull(() -> {
            //先在远程确认一下更新日期是否相同
            ArrayList<CardItem> value = remote.userRequest.Sync_CardRepository(user.getId(),user.getCardRepository_update());
            ArrayList<SkillCard> skillCards = new ArrayList<>();
            ArrayList<Long> none = new ArrayList<>();
            if(value != null){
                local.db.cardRepositoryDao().deleteAllSync();
                local.db.cardRepositoryDao().insert(value.toArray(new CardItem[0]));
            }
            else value = new ArrayList<>(local.db.cardRepositoryDao().queryByIdSync(user.getId()));
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

    public void local_updateAccount(User user) {
        RxVoid(()->local.db.userDao().updateAccount(user.getId(), user.getUsername(), user.getPassword()));
    }
    public void local_updateCardRepositoryUpdate(User user) {
        RxVoid(()->local.db.userDao().updateCardRepositoryUpdate(user.getId(), user.getCardRepository_update()));
    }
    public void local_updateFriendUpdate(User user) {
        RxVoid(()->local.db.userDao().updateFriendUpdate(user.getId(), user.getCardRepository_update()));
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

    public Single<Boolean> inviteSquad(long id){
        return Rx(()->remote.userRequest.InviteSquad(id));
    }
    public Single<ArrayList<User>> enterSquad(long id,String secretKey){
        return Rx(()->remote.userRequest.EnterSquad(id,secretKey));
    }
    public Single<String> createSquad(String roomType){
        return Rx(()->remote.userRequest.CreateSquad(roomType));
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
