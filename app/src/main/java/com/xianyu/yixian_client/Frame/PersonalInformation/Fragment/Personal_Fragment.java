package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalInformationViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalInformationPersonalFragmentBinding;

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
public class Personal_Fragment extends Fragment {
    @Inject
    public PersonalInformationViewModel viewModel;
    public PersonalInformationPersonalFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonalInformationPersonalFragmentBinding.inflate(inflater,container,false);
        init();
        return binding.getRoot();
    }

    private void init() {
        TextView textView = binding.getRoot().findViewById(R.id.kills_text);
        textView.setText(Core.liveUser.getValue().getKills());

        TextView textView1 = binding.getRoot().findViewById(R.id.battle_text);
        textView.setText(Core.liveUser.getValue().getBattle_Count());

        TextView textView2 = binding.getRoot().findViewById(R.id.textView8);
        textView.setText(Core.liveUser.getValue().getDeaths());

        TextView textView3 = binding.getRoot().findViewById(R.id.title_text);
        textView.setText(Core.liveUser.getValue().getTitle());

        TextView textView4 = binding.getRoot().findViewById(R.id.lv_text);
        textView.setText(Core.liveUser.getValue().getLv());

        TextView textView5 = binding.getRoot().findViewById(R.id.history_exp);
        textView.setText(Core.liveUser.getValue().getExp());

        TextView textView6 = binding.getRoot().findViewById(R.id.time_text);
        textView.setText((int) Core.liveUser.getValue().getRegistration_date());





    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
