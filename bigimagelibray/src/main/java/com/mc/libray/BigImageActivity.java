package com.mc.libray;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.mc.libray.photoview.PhotoView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by MC on 2017/11/28.
 * 大图主页面
 */


public class BigImageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView title;
    private BigImageAdapter mAdapter;
    private FrameLayout content;
    private FrameLayout before;
    private FrameLayout parent;

    private List<String> images;//图片地址集合
    private List<String> placeHolderUrl;//小图地址
    private List<Location> placeHolderLocation;//小图坐标
    private int startPosition;//开始位置
    private int currentIndex;//选中图片
    private BigImageBuilder builder;
    private ImageView beforeView;//模拟动画缩略图
    private BigImageAnimator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        title = (TextView) findViewById(R.id.title);
        content = (FrameLayout) findViewById(R.id.content);
        before = (FrameLayout) findViewById(R.id.before);
        parent = (FrameLayout) findViewById(R.id.parent);
        parent.setBackgroundColor(getResources().getColor(R.color.m_black_one));
        builder = getIntent().getParcelableExtra("builder");
        animator = new BigImageAnimator();
        initData();
        startshowAnimation();
        initViewPager();
        initEvent();
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                title.setText(position + 1 + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnimation();
            }
        });
    }

    private void initData() {
        images = builder.getImages();
        placeHolderUrl = builder.getPlaceHolderUrl();
        currentIndex = builder.getCurrentIndex();
        placeHolderLocation = builder.getPlaceHolderLocation();
        startPosition=builder.getStartPosition();
    }

    private void initViewPager() {
        title.setText(currentIndex + 1 + "/" + images.size());
        mAdapter = new BigImageAdapter(this, images, placeHolderUrl);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(currentIndex);
    }

    /**
     * 展示动画
     */
    private void startshowAnimation() {
        addToWindow();
        Animator showAnima = animator.showAnimator(placeHolderLocation.get(currentIndex-startPosition), this, beforeView);
        showAnima.start();
        showAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                content.setVisibility(View.VISIBLE);
                before.removeAllViews();
                before.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    /**
     * 退出动画
     */
    public void endAnimation() {
        View view = mAdapter.getPrimaView();
        PhotoView beforeView =  view.findViewById(R.id.photoview);
        Animator backAnima = animator.dismissBackgroundAnimator(parent);
        Animator endAnima;
        if (currentIndex-startPosition > placeHolderLocation.size()-1||currentIndex<startPosition) {//缩略图与大图不能对应
            endAnima = animator.dismissMissAnimator(beforeView);
        } else {
            endAnima = animator.dismissAnimator(beforeView, placeHolderLocation.get(currentIndex-startPosition));
        }
        backAnima.start();
        endAnima.start();
        endAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                parent.setBackgroundColor(getResources().getColor(R.color.translate));

                BigImageActivity.this.finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 添加imageview
     */
    private void addToWindow() {
        beforeView = new ImageView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(placeHolderLocation.get(currentIndex-startPosition).getWidth(), placeHolderLocation.get(currentIndex-startPosition).getHeight());
        beforeView.setLayoutParams(lp);
        beforeView.setX(placeHolderLocation.get(currentIndex-startPosition).getX());
        beforeView.setY(placeHolderLocation.get(currentIndex-startPosition).getY() - getStatusBarHeight(this));
        ImageLoader imageLoader = new ImageLoader(this);
        imageLoader.placeHolder(placeHolderUrl.get(currentIndex), beforeView);
        before.addView(beforeView);
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object object = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(object);
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void onBackPressed() {
        endAnimation();
    }
}
