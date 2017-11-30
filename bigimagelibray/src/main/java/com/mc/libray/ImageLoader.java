package com.mc.libray;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by MC on 2017/11/28.
 * 下载器
 */

public class ImageLoader {

    private Context context;
    private Bitmap bm=null;

    public ImageLoader(Context context) {
        this.context = context;
    }

    public void LoadImage(String URL, ImageView imageView,Bitmap bitmap) {
        Picasso.with(context).load(URL).placeholder(new BitmapDrawable(bitmap)).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                if (onLoading!=null){
                    onLoading.onSuccess();
                }
            }

            @Override
            public void onError() {
                if (onLoading!=null){
                    onLoading.onError();
                }
            }
        });
    }

    public void placeHolder(String URL, final ImageView imageView) {
        Picasso.with(context).load(URL).into(imageView);
    }

    public Bitmap getBitmap(String Url){
        Picasso.with(context).load(Url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bm=bitmap;
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        return bm;
    }
    private onLoading onLoading;

    public void setOnLoadingLister(onLoading onLoading) {
        this.onLoading = onLoading;
    }

    interface onLoading {
        void onSuccess();

        void onError();
    }
}
