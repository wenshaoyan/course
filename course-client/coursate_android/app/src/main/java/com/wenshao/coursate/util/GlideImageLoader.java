package com.wenshao.coursate.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.wenshao.coursate.R;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by Administrator on 2017/9/28.
 * 轮播图 图片加载器
 */

public class GlideImageLoader extends com.youth.banner.loader.ImageLoader {
    private ImageLoader mImageLoader;
    private DisplayImageOptions mOptions;
    public GlideImageLoader(){
        //显示图片的配置
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.guide_1_1)
                .showImageOnFail(R.drawable.guide_1_2)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mImageLoader = ImageLoader.getInstance();

    }
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        mImageLoader.displayImage((String)path, imageView, mOptions);
    }
}
