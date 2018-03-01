package com.example.guojiawei.finderproject.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.ui.activity.CenterActivity;

import java.io.File;


public class BitMapUtil {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_loading)
                // .error(R.drawable.ic_loading)
                //.centerCrop()
//                .transform(new GlideCircleTransform(context))
//                .transform(new GlideRoundTransform(context, 5))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadCycleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_loading)
                // .error(R.drawable.ic_loading)
                //.centerCrop()
//                .transform(new GlideRoundTransform(context, 5))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImage(Context context, int url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new GlideRoundTransform(context, 5))

                //  .placeholder(R.drawable.ic_loading)
                // .error(R.drawable.ic_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadImage(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .transform(new GlideRoundTransform(context, 5))

                //.centerCrop()
                //  .placeholder(R.drawable.ic_loading)
                // .error(R.drawable.ic_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
