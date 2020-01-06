package com.lugq.appframedemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

@SuppressWarnings("unused")
public class GlideUtils {

    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(iv);
    }

    /**
     * 使用场景：用Glide作为图片加载框架，图片的URI地址不变，
     * 每次更换图片时，都存在缓存情况，加载不出来最新图片
     * https://blog.csdn.net/qq_32519693/article/details/78067726
     */
    public static void loadNoCache(String url, ImageView imageView) {
        Glide.with(ContextHolder.getContext()).load(url)
                //.error(R.mipmap.icon_course_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

}
