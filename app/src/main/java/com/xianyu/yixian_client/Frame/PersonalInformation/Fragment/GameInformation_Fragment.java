package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalInformationViewModel;
import com.xianyu.yixian_client.databinding.PersonalInformationGameInformationFragmentBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Login
 * @ClassName: Login_Fragment
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2020/12/18 21:51
 * @UpdateUser: Jianxian
 * @UpdateDate: 2020/12/18 21:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@AndroidEntryPoint
public class GameInformation_Fragment extends Fragment {
    @Inject
    public PersonalInformationViewModel viewModel;
    public PersonalInformationGameInformationFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonalInformationGameInformationFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
