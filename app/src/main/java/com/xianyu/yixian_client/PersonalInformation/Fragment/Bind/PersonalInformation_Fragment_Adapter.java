package com.xianyu.yixian_client.PersonalInformation.Fragment.Bind;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.xianyu.yixian_client.Login.LoginViewModel;
import com.xianyu.yixian_client.PersonalInformation.Fragment.GameInformation_Fragment;
import com.xianyu.yixian_client.PersonalInformation.Fragment.PersonalInformationViewModel;
import com.xianyu.yixian_client.PersonalInformation.Fragment.Personal_Fragment;
import com.xianyu.yixian_client.PersonalInformation.Fragment.History_Fragment;

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
public class PersonalInformation_Fragment_Adapter extends FragmentStateAdapter {
    private static final int NUM_LOGIN_FRAGMENT = 3;
    private FragmentActivity activity;
    private PersonalInformationViewModel loginViewModel;

    public PersonalInformation_Fragment_Adapter(FragmentActivity activity, PersonalInformationViewModel loginViewModel) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.loginViewModel = loginViewModel;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new History_Fragment(loginViewModel);
            case 1:return new Personal_Fragment(loginViewModel);
            case 2:return new GameInformation_Fragment(loginViewModel);
            default:return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_LOGIN_FRAGMENT;
    }
}
