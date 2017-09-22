package com.bwie.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.fragment.ReMen;
import com.bwie.test.fragment.ZhuTi;
import com.bwie.test.fragment.ZhuanLan;
import com.bwie.test.fragment.ZuiXin;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *每个Fragment  tablayout和viewpager联动
 * Created by zhanggang on 2017/9/22.
 */

public class ZhuYe extends Fragment {

    @BindView(R.id.tablayout_zhuye)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_zhuye)
    ViewPager viewPager;
    String [] title={"最新日报","专栏","热门","主题日报"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuye_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置viewpager适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment=new ZuiXin();
                        break;
                    case 1:
                        fragment=new ZhuanLan();
                        break;
                    case 2:
                        fragment=new ReMen();
                        break;
                    case 3:
                        fragment=new ZhuTi();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
            //添加tablayout文字标题
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
