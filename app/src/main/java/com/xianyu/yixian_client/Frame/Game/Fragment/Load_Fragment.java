package com.xianyu.yixian_client.Frame.Game.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.xianyu.yixian_client.Frame.Game.GameViewModel;
import com.xianyu.yixian_client.databinding.GameLoadFragmentBinding;

/**
 * @ProjectName: YiXian_Client
 * @Package: com.xianyu.yixian_client.Frame.Game.Fragment
 * @ClassName: Load_Fragment
 * @Description: java类作用描述
 * @Author: Jianxian
 * @CreateDate: 2021/3/4 20:01
 * @UpdateUser: Jianxian
 * @UpdateDate: 2021/3/4 20:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Load_Fragment extends Fragment {
    private GameLoadFragmentBinding binding;
    private ViewPager2 paper;
    GameViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = GameLoadFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        init();
        return binding.getRoot();
    }

    private void init() {

    }
}

