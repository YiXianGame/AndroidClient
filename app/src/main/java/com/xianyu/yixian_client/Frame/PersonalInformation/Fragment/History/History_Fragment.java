package com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.xianyu.yixian_client.Frame.PersonalInformation.Fragment.History.Adapt.HistoryAdapt;
import com.xianyu.yixian_client.Frame.PersonalInformation.PersonalProfileViewModel;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalProfileHistoryFragmentBinding;
import com.xianyu.yixian_client.databinding.PersonalProfileHistoryItemBinding;
import com.yixian.make.Core;

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
public class History_Fragment extends Fragment  {

    private PersonalProfileViewModel viewModel;
    PersonalProfileHistoryFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PersonalProfileHistoryFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(PersonalProfileViewModel.class);
        init(Core.liveUser.getValue().getId());
        return binding.getRoot();
    }

    private void init(long user_id) {
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.history_list);
        HistoryAdapt historyAdapt = new HistoryAdapt();
        historyAdapt.setAnimationEnable(true);
        historyAdapt.setAnimationFirstOnly(false);
        historyAdapt.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        historyAdapt.setHeaderView(PersonalProfileHistoryItemBinding.inflate(getLayoutInflater(),binding.getRoot(),false).getRoot());
        recyclerView.setAdapter(historyAdapt);
        viewModel.queryUserByID(user_id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(user -> {
                    historyAdapt.setDiffNewData(user.getHistory());
                });
    }

    @Override
    public void onDestroy() {
        onDestroyView();
        super.onDestroy();
    }
}
