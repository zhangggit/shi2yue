package com.bwie.test;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Banner添加图片的
 * Created by zhanggang on 2017/9/22.
 */

public class GlideImageLoader extends com.youth.banner.loader.ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
