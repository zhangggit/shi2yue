package com.bwie.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.App;
import com.bwie.test.R;
import com.bwie.test.adapter.MyGridViewAdapter;
import com.bwie.test.bean.ZhuTiBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *主题页面
 * Created by zhanggang on 2017/9/22.
 */

public class ZhuTi extends Fragment {
    @BindView(R.id.recyclerview_zhuti)
    RecyclerView recyclerView;
    String urlPath="http://news-at.zhihu.com/api/4/themes";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuti_fragment,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    private void initData() {
        OkHttpClient okHttpClient = App.send();
        Request request = new Request.Builder()
                .url(urlPath).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ZhuTiBean zhuTiBean = new Gson().fromJson(response.body().string(), ZhuTiBean.class);
                //获取集合
                final List<ZhuTiBean.OthersBean> others = zhuTiBean.others;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置适配器
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(),others);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }
}
