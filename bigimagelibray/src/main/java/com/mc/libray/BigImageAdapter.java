package com.mc.libray;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;



import com.mc.libray.photoview.PhotoView;

import java.util.List;

/**
 * Created by MC on 2017/11/28.
 * 适配器
 */

public class BigImageAdapter extends PagerAdapter {
    private BigImageActivity activity;
    private List<String> images;
    private List<String> placeHolder;


    public BigImageAdapter(BigImageActivity bigImageActivity, List<String> images, List<String> placeHolderUrl) {
        activity = bigImageActivity;
        this.images = images;
        this.placeHolder = placeHolderUrl;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(activity, R.layout.item_bigimg_layout, null);
        addLoadView(view, position);
        container.addView(view);
        return view;
    }

    private void addLoadView(View view, int position) {
        PhotoView photoView = view.findViewById(R.id.photoview);
        photoView.enable();
        final ProgressBar progressBar = view.findViewById(R.id.progressbar);
        ImageLoader imageLoader = new ImageLoader(activity);
        Bitmap bitmap=null;
        if (position<placeHolder.size()){
            bitmap=imageLoader.getBitmap(placeHolder.get(position));
        }
        imageLoader.setOnLoadingLister(new ImageLoader.onLoading() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
            }
        });
        imageLoader.LoadImage(images.get(position), photoView,bitmap);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.endAnimation();
            }
        });
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    //获取当前显示的view视图
    public View getPrimaView() {
        return mCurrentView;
    }
}
