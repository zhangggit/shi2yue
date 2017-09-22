package com.bwie.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.R;
import com.bwie.test.bean.ZuiXinBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *最新页面 适配器
 * Created by zhanggang on 2017/9/22.
 */

public class ZuiXinAdapter extends XRecyclerView.Adapter<ZuiXinAdapter.ViewHolder> {

    Context context;
    List<ZuiXinBean.TopStoriesBean> list;

    public ZuiXinAdapter(Context context, List<ZuiXinBean.TopStoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.zuixnrecycler_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ga_prefix.setText(list.get(position).ga_prefix);
        holder.title.setText(list.get(position).title);
        ImageLoader.getInstance().displayImage(list.get(position).getImage(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //内部类
    class ViewHolder extends XRecyclerView.ViewHolder{
        @BindView(R.id.ga_prefix)
        TextView ga_prefix;
        @BindView(R.id.recyclerview_title)
        TextView title;
        @BindView(R.id.recyclerview_image)
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
