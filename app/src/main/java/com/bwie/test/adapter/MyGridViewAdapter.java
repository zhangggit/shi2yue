package com.bwie.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.BitmapUtils;
import com.bwie.test.Main2Activity;
import com.bwie.test.R;
import com.bwie.test.bean.ZhuTiBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主题页gridview适配器
 * Created by zhanggang on 2017/9/22.
 */

public class MyGridViewAdapter extends RecyclerView.Adapter<MyGridViewAdapter.ViewHolder> {

    Context context;
    List<ZhuTiBean.OthersBean> list;

    public MyGridViewAdapter(Context context, List<ZhuTiBean.OthersBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).name);
        //加载圆角图片
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(20,20))
                .build();
        ImageLoader.getInstance().displayImage(list.get(position).thumbnail,holder.imageView,options);
        //点击事件
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                context.startActivity(intent);
            }
        });
//        Bitmap bitmap = BitmapUtils.getBitmap(list.get(position).thumbnail, 50, 50);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.grid_item_image)
        ImageView imageView;
        @BindView(R.id.grid_item_name)
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
