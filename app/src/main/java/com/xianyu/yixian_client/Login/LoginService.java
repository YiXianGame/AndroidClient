package com.xianyu.yixian_client.Login;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.xianyu.yixian_client.R;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Login
 * @ClassName: RepositoryService
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/23 18:06
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/23 18:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginService extends Service {
    MediaPlayer mediaPlayer;
//0000000
    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.b);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
