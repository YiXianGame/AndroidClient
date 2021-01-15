package com.xianyu.yixian_client.PersonalInformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.xianyu.yixian_client.Core;
import com.xianyu.yixian_client.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.PersonalInformation.Fragment.Bind.PersonalInformation_Fragment_Adapter;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.PersonalInformationActivityBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@AndroidEntryPoint
public class PersonalInformation_Activity extends AppCompatActivity {
    PersonalInformationActivityBinding personalInformationActivityBinding;
    @Inject
    PersonalInformationViewModel viewModel;
    ViewPager2 paper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personalInformationActivityBinding = PersonalInformationActivityBinding.inflate(getLayoutInflater());
        setContentView(personalInformationActivityBinding.getRoot());
        viewModel.repository.queryUserById(123456)
                .subscribeOn(Schedulers.io())//查询数据时的线程
                .observeOn(AndroidSchedulers.mainThread())//数据查找完毕的线程
                .subscribe(user -> {
                    Core.liveUser.setValue(user);
                    //fragment绑定初始化
                    paper = findViewById(R.id.paper);
                    paper.setPageTransformer(new DepthPageTransformer());
                    paper.setAdapter(new PersonalInformation_Fragment_Adapter(this));

                });
    }

    public void personal_Click(View view) {
        paper.setCurrentItem(1);
    }

    public void history_Click(View view) {
        paper.setCurrentItem(0);
    }

    public void information_Click(View view) {
        paper.setCurrentItem(2);
    }
}