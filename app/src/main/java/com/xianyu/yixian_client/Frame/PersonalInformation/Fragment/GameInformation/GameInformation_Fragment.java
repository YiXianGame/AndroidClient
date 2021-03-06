package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.GameInformation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalProfileViewModel;
import com.xianyu.yixian_client.databinding.PersonalProfilePersonalSignatureFragmentBinding;

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
public class GameInformation_Fragment extends Fragment {
    private PersonalProfileViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PersonalProfilePersonalSignatureFragmentBinding binding = PersonalProfilePersonalSignatureFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(PersonalProfileViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
