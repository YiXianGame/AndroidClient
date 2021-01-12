package com.xianyu.yixian_client.Login.Fragment.Bind;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.xianyu.yixian_client.Login.Fragment.Forget_Fragment;
import com.xianyu.yixian_client.Login.Fragment.Login_Fragment;
import com.xianyu.yixian_client.Login.Fragment.Register_Fragment;
import com.xianyu.yixian_client.Login.LoginViewModel;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Login
 * @ClassName: PersonalInformation_Fragment_Adapter
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/18 22:34
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/18 22:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Login_Fragment_Adapter extends FragmentStateAdapter {
    private static final int NUM_LOGIN_FRAGMENT = 3;
    private FragmentActivity activity;
    private LoginViewModel loginViewModel;

    public Login_Fragment_Adapter(FragmentActivity activity,LoginViewModel loginViewModel) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.loginViewModel = loginViewModel;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new Register_Fragment(loginViewModel);
            case 1:return new Login_Fragment(loginViewModel);
            case 2:return new Forget_Fragment(loginViewModel);
            default:return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_LOGIN_FRAGMENT;
    }
}
