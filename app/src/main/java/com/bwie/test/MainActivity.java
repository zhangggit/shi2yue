package com.bwie.test;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 主页 底部的Fragment viewpager联动
 * by zhanggang
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_main)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_main)
    ViewPager viewPager;
    String [] title={"主页","主页1","主页2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new ZhuYe();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }
}
