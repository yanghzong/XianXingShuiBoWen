package com.bw.zhoukao_moni1;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.zhoukao_moni1.Fragment.Fragment_fenlei;
import com.bw.zhoukao_moni1.Fragment.Fragment_first;
import com.bw.zhoukao_moni1.Fragment.Fragment_mine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpZhu;
    private TabLayout tbBottom;
    private List<Fragment> fragmentlist;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new Fragment_first());
        fragmentlist.add(new Fragment_fenlei());
        fragmentlist.add(new Fragment_mine());

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("分类");
        titles.add("我的");

        vpZhu.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentlist.get(position);
            }

            @Override
            public int getCount() {
                return fragmentlist.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        tbBottom.setupWithViewPager(vpZhu);
        tbBottom.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initView() {
        vpZhu = findViewById(R.id.vp_zhu);
        tbBottom = findViewById(R.id.tb_bottom);


    }
}
