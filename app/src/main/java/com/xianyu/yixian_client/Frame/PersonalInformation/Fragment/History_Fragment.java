package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.Adapt.HistoryAdapt;
import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalInformationViewModel;
import com.xianyu.yixian_client.Model.Repository.Repository;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalInformationHistoryFragmentBinding;

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
public class History_Fragment extends Fragment  {

    private PersonalInformationViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PersonalInformationHistoryFragmentBinding binding = PersonalInformationHistoryFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(PersonalInformationViewModel.class);
        init(binding.getRoot());
        return binding.getRoot();
    }

    private void init(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.history_list);
        HistoryAdapt historyAdapt = new HistoryAdapt(Core.liveUser.getValue().getHistory());
        recyclerView.setAdapter(historyAdapt);
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
