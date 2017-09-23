package com.bwie.test.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bwie.test.App;
import com.bwie.test.GlideImageLoader;
import com.bwie.test.R;
import com.bwie.test.adapter.ZuiXinAdapter;
import com.bwie.test.bean.ZuiXinBean;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 最新日报界面
 * Created by zhanggang on 2017/9/22.
 */

public class ZuiXin extends Fragment {

    String urlPath = "http://news-at.zhihu.com/api/4/news/latest";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private ZuiXinAdapter adapter;
    private List<ZuiXinBean.TopStoriesBean> top_stories1 = new ArrayList<>();
    final List<String> list_image = new ArrayList<>();

    private SwipyRefreshLayout srl;
    private Handler handler = null;
    String url = "http://news-at.zhihu.com/api/4/news/before/20131119";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zuixin_fragment, container, false);
        ButterKnife.bind(this, view);
        handler = new Handler();
        srl = (SwipyRefreshLayout) view.findViewById(R.id.srl);

        srl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, android.R.color.holo_red_dark);
        //设置是否支持刷新和加载更多
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_image.clear();
                        initData();
                        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }

            //上啦加载
            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                        initData();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        //设置适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ZuiXinAdapter(getActivity(), top_stories1);
        recyclerView.setAdapter(adapter);


    }

    //获取网络数据 图片
    private void initData() {
        OkHttpClient okHttpClient = App.send();
        Request request = new Request.Builder()
                .url(urlPath)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ZuiXinBean zuiXinBean = new Gson().fromJson(response.body().string(), ZuiXinBean.class);
                top_stories1.addAll(zuiXinBean.top_stories);

                List<ZuiXinBean.TopStoriesBean> top_stories = zuiXinBean.top_stories;
                for (int i = 0; i < top_stories.size(); i++) {
                    list_image.add(top_stories.get(i).getImage());
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        banner.setImages(list_image);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                    }
                });


            }
        });

    }
}
