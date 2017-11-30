package com.mc.libray;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Created by MC on 2017/1/19.
 * 动画
 */
public class BigImageAnimator implements AnimaterInterface {


    @Override
    public Animator showAnimator(Location beforeView, Context context, View animaView) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels - getStatusBarHeight(context);

        int startTranX = beforeView.getX();
        int endTranX = (widthPixels - beforeView.getWidth()) / 2;

        int startTranY = beforeView.getY() - getStatusBarHeight(context);
        int endTranY = (heightPixels - beforeView.getHeight()) / 2;


        // x 方向平移
        ObjectAnimator tranXAnima = ObjectAnimator.ofFloat(animaView, "x", startTranX, endTranX);
        // y 方向平移
        ObjectAnimator tranYAnima = ObjectAnimator.ofFloat(animaView, "y", startTranY, endTranY);

        AnimatorSet animatorSet = new AnimatorSet();
        //平移同时放大
//        animatorSet.play(tranXAnima)
//                .with(tranYAnima).with(scaleXAnima).with(scaleYAnima);

        //只平移不放大
        animatorSet.play(tranXAnima)
                .with(tranYAnima);
        animatorSet.setDuration(500);
        animatorSet.setStartDelay(65);
        return animatorSet;
    }

    @Override
    public Animator dismissAnimator(View beforeView, Location afterView) {

        int statusBar=getStatusBarHeight(beforeView.getContext());
        float endScale = afterView.getWidth() * 1f / beforeView.getWidth();
        float endScaleY = (afterView.getHeight() * 1.f / beforeView.getHeight());
        float endTranX = (beforeView.getWidth() - (beforeView.getWidth() * endScale)) * .5f - afterView.getX();
        float endTranY = (beforeView.getHeight() - (beforeView.getHeight() * endScaleY)) * .5f
                - (afterView.getY() - getStatusBarHeight(beforeView.getContext()));

        // x 方向缩小
        ObjectAnimator scaleXAnima = ObjectAnimator.ofFloat(beforeView, "scaleX", beforeView.getScaleX(), endScale);
        // y 方向缩小
        ObjectAnimator scaleYAnima = ObjectAnimator.ofFloat(beforeView, "scaleY", beforeView.getScaleY(), endScale);
        // x 方向平移
        ObjectAnimator tranXAnima = ObjectAnimator.ofFloat(beforeView, "x", beforeView.getTranslationX(), -endTranX);
        // y 方向平移
        ObjectAnimator tranYAnima = ObjectAnimator.ofFloat(beforeView, "y", beforeView.getTranslationY()-statusBar*1f, -endTranY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.play(scaleXAnima)
                .with(scaleYAnima)
                .with(tranXAnima)
                .with(tranYAnima);
        return animatorSet;
    }


    @Override
    public Animator dismissMissAnimator(final View beforeView) {
        ValueAnimator missAnimator = ValueAnimator.ofFloat(0, 1f);
        missAnimator.setInterpolator(new AccelerateInterpolator());
        missAnimator.setDuration(350);
        missAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float scale = .5f * fraction + 1.f;
                float alpha = 1.f - fraction;

                beforeView.setScaleX(scale);
                beforeView.setScaleY(scale);
                beforeView.setAlpha(alpha);

            }
        });
        return missAnimator;
    }

    @Override
    public Animator dismissBackgroundAnimator(final View parent) {
        ValueAnimator backAnimator = ValueAnimator.ofFloat(0, 1f);
        backAnimator.setInterpolator(new AccelerateInterpolator());
        backAnimator.setDuration(350);
        backAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) (255 * (1.f - animation.getAnimatedFraction()));
                parent.getBackground().setAlpha(alpha);
            }
        });
        return backAnimator;
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

}
