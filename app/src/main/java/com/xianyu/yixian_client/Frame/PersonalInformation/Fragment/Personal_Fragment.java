package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalProfileViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalProfilePersonalFragmentBinding;

import java.util.Locale;

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
public class Personal_Fragment extends Fragment {
    
    public PersonalProfileViewModel viewModel;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PersonalProfilePersonalFragmentBinding binding = PersonalProfilePersonalFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(PersonalProfileViewModel.class);
        init(binding.getRoot());
        return binding.getRoot();
    }

    private void init(View view) {
        Core.liveUser.observe(getViewLifecycleOwner(), user -> {
            TextView kills_text = view.findViewById(R.id.kills_text_num);
            kills_text.setText(String.format(Locale.getDefault(),"%d",user.getKills()));

            TextView battle_text = view.findViewById(R.id.battle_count_text);
            battle_text.setText(String.format(Locale.getDefault(),"$d",user.getBattle_Count()));

            TextView deaths_text = view.findViewById(R.id.deaths_num_text);
            deaths_text.setText(String.format(Locale.getDefault(),"$d",user.getDeaths()));

        });
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
