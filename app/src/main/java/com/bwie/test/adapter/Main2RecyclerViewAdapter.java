package com.bwie.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.R;
import com.bwie.test.bean.ZhuTiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 详情页适配器  多条目
 * Created by zhanggang on 2017/9/22.
 */

public class Main2RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ZhuTiBean.OthersBean> list;

    public Main2RecyclerViewAdapter(Context context, List<ZhuTiBean.OthersBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.xiangqing_item, parent, false);
                return new ViewHolder(inflate);
            default:
                View inflate2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.xiangqing_item2, parent, false);
                return new ViewHolder2(inflate2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder mholder = (ViewHolder) holder;
            mholder.description.setText(list.get(position).description);
            mholder.name.setText(list.get(position).name);
            Glide.with(context).load(list.get(position).thumbnail).into(mholder.imageView);
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 mholder = (ViewHolder2) holder;
            mholder.name2.setText(list.get(position).name);
            mholder.description2.setText(list.get(position).description);
        }
    }

    //内部类 多条目1
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xiangqing_description)
        TextView description;
        @BindView(R.id.xiangqing_color)
        TextView color;
        @BindView(R.id.xiangqing_name)
        TextView name;
        @BindView(R.id.xiangqing_image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //内部类 多条目2
    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.item2_description)
        TextView description2;
        @BindView(R.id.item2_name)
        TextView name2;

        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
