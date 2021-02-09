package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalProfileViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalProfilePersonalFragmentBinding;
import com.yixian.make.Core;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    PersonalProfilePersonalFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonalProfilePersonalFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(PersonalProfileViewModel.class);
        init(Core.liveUser.getValue().getId());
        return binding.getRoot();
    }

    private void init(long user_id) {
        viewModel.queryUserByID(user_id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
            .subscribe(user -> {
                TextView kills_text = binding.getRoot().findViewById(R.id.kills_num_text);
                kills_text.setText(String.format(Locale.getDefault(),"杀敌数:%d",user.getKills()));

                TextView battle_text = binding.getRoot().findViewById(R.id.battle_count_num_text);
                battle_text.setText(String.format(Locale.getDefault(),"战斗场次:%d",user.getBattleCount()));

                TextView deaths_text = binding.getRoot().findViewById(R.id.deaths_num_text);
                deaths_text.setText(String.format(Locale.getDefault(),"死亡数:%d",user.getDeaths()));

                TextView nickname_text = binding.getRoot().findViewById(R.id.nickname_text);
                nickname_text.setText(user.getNickname());
            });
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
