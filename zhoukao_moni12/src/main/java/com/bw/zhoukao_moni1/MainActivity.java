package com.bw.zhoukao_moni1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bw.zhoukao_moni1.Fragment.HomeFragment;
import com.bw.zhoukao_moni1.Fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpZhu;
    private List<Fragment> list;
    private TabLayout tbShow;
    private List<String> titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MineFragment());
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("我的");

        vpZhu.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }


        });
        tbShow.setTabMode(TabLayout.MODE_FIXED);
        tbShow.setupWithViewPager(vpZhu);




}

    private void initView() {
        vpZhu = findViewById(R.id.vp_zhu);
        tbShow = findViewById(R.id.tb_show);
    }
}
