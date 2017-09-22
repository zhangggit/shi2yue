package com.bwie.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.test.adapter.Main2RecyclerViewAdapter;
import com.bwie.test.bean.UrlUtils;
import com.bwie.test.bean.ZhuTiBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//详情页面
public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.recyclerview_main2)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //添加数据
        initData();

    }

    private void initData() {
        UrlUtils.sendOkHttpRequest("http://news-at.zhihu.com/api/4/themes", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ZhuTiBean zhuTiBean = new Gson().fromJson(response.body().string(), ZhuTiBean.class);
                final List<ZhuTiBean.OthersBean> others = zhuTiBean.others;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //适配器
                        Main2RecyclerViewAdapter adapter = new Main2RecyclerViewAdapter(Main2Activity.this,others);
                        recyclerView.setAdapter(adapter);
                    }
                });

            }
        });
    }
}
