package com.xianyu.yixian_client.Frame.Repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.xianyu.yixian_client.Model.Room.Entity.Buff;
import com.xianyu.yixian_client.Model.Room.Entity.SkillCard;
import com.xianyu.yixian_client.R;
import com.xianyu.yixian_client.databinding.RepositoryActivityBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RepositoryActivity extends AppCompatActivity {
    RepositoryActivityBinding binding;
    @Inject
    RepositoryViewModel repositoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_activity);
        //初始化
        Init();
    }

    private void Init() {
        List<SkillCard> cards = new ArrayList();

        Buff buff = new Buff();
        SkillCard skillCard = new SkillCard();
        buff.setName("格挡");
        skillCard.getBuffs().add(buff);
        buff = new Buff();
        buff.setName("燃烧");
        skillCard.getBuffs().add(buff);
        skillCard.setName("气仙盾");
        skillCard.setCure(true);
        skillCard.setDescription("盾出挡势功");
        skillCard.setEnemy_mp(10);
        skillCard.setAuxiliary_hp(1);
        skillCard.setAuxiliary_mp(1);
        skillCard.setMp(10);
        cards.add(skillCard);

        skillCard = new SkillCard();
        skillCard.setName("气斩");
        buff = new Buff();
        skillCard.setPhysics(true);
        skillCard.setAttack(true);
        skillCard.setDescription("奋力一斩，是剑术中的绝对一种势功  ");
        skillCard.setEnemy_hp(38);
        skillCard.setProbability(10);
        skillCard.setAuxiliary_hp(1);
        skillCard.setAuxiliary_mp(1);
        skillCard.setMp(12);
        cards.add(skillCard);

        skillCard = new SkillCard();
        skillCard.setName("气羽化");
        buff = new Buff();
        buff.setName("无敌");
        skillCard.getBuffs().add(buff);
        skillCard.setMagic(true);
        skillCard.setDescription("羽化而登仙，仙者不可灭");
        skillCard.setAuxiliary_hp(40);
        skillCard.setProbability(20);
        skillCard.setAuxiliary_hp(1);
        skillCard.setAuxiliary_mp(1);
        skillCard.setMp(10);
        cards.add(skillCard);

        CardAdapt cardAdapt = new CardAdapt(cards);
        RecyclerView recyclerView = findViewById(R.id.card_frame);
        recyclerView.setAdapter(cardAdapt);
    }
}