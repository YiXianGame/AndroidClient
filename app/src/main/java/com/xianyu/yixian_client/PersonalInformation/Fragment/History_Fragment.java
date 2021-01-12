package com.xianyu.yixian_client.PersonalInformation.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Login.LoginViewModel;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.Model.Room.Entity.User;
import com.xianyu.yixian_client.PersonalInformation.Fragment.Adapt.HistoryAdapt;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalInformationHistoryFragmentBinding;
import com.xianyu.yixian_client.databinding.RegisterFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Login
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
public class History_Fragment extends Fragment  {
    public PersonalInformationViewModel viewModel;
    public PersonalInformationHistoryFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonalInformationHistoryFragmentBinding.inflate(inflater,container,false);
        init();
        return binding.getRoot();
    }

    private void init() {
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.history_list);
        viewModel.repositoryFactory.queryHistory(12345)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(histories -> {
                    HistoryAdapt adapt = new HistoryAdapt(histories);
                    recyclerView.setAdapter(adapt);
                });
    }

    public History_Fragment(PersonalInformationViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
