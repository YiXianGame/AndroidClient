package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.Bind;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.GameInformation_Fragment;
import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.Personal_Fragment;
import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.History_Fragment;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Login
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

    public PersonalInformation_Fragment_Adapter(FragmentActivity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new History_Fragment();
            case 1:return new Personal_Fragment();
            case 2:return new GameInformation_Fragment();
            default:return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_LOGIN_FRAGMENT;
    }
}
