package com.xianyu.yixian_client.PersonalInformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xianyu.yixian_client.Login.Fragment.Bind.DepthPageTransformer;
import com.xianyu.yixian_client.Login.Fragment.Bind.Login_Fragment_Adapter;
import com.xianyu.yixian_client.Login.LoginViewModel;
import com.xianyu.yixian_client.Model.Room.Entity.History;
import com.xianyu.yixian_client.PersonalInformation.Fragment.Bind.PersonalInformation_Fragment_Adapter;
import com.xianyu.yixian_client.PersonalInformation.Fragment.PersonalInformationViewModel;
import com.xianyu.yixian_client.PersonalInformation.Fragment.Personal_Fragment;
import com.xianyu.yixian_client.R;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PersonalInformation_Activity extends AppCompatActivity {
    @Inject
    PersonalInformationViewModel viewModel;
    ViewPager2 paper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_activity);
        //fragment绑定初始化
        paper = findViewById(R.id.paper);
        paper.setPageTransformer(new DepthPageTransformer());
        paper.setAdapter(new PersonalInformation_Fragment_Adapter(this,viewModel));
        initData();
        int a;
    }

    private void initData() {
        History history = new History();
        history.setKills(12);
        history.setUser_id(12345);
        history.setResult(false);
        viewModel.repositoryFactory.insertHistory(history);
        history = new History();
        history.setKills(12);
        history.setUser_id(12345);
        history.setResult(true);
        viewModel.repositoryFactory.insertHistory(history);
        history = new History();
        history.setKills(24);
        history.setUser_id(12345);
        history.setResult(false);
        viewModel.repositoryFactory.insertHistory(history);
        history = new History();
        history.setKills(1);
        history.setUser_id(12345);
        history.setResult(true);
        viewModel.repositoryFactory.insertHistory(history);
        history = new History();
        history.setKills(2);
        history.setUser_id(12345);
        history.setResult(true);
        viewModel.repositoryFactory.insertHistory(history);
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