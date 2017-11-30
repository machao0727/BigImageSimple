package com.mc.libray;

import android.animation.Animator;
import android.content.Context;
import android.view.View;

/**
 * Created by MC on 2017/11/29.
 * 动画接口
 */

public interface AnimaterInterface {
    /**
     * 点击缩略图时创建一个开启并展现 TransferImage 的动画
     *
     * @param beforeView 缩略图坐标对象
     * @param animaView  动画对象
     * @return 显示 TransferImage 的属性动画
     */
    Animator showAnimator(Location beforeView, Context context, View animaView);


    /**
     * 创建点击关闭 TransferImage 与之前缩略图可对应的动画
     *
     * @param beforeView TransferImage 中当前所显示的图片对象
     * @param afterView  缩略图坐标信息
     * @return TransferImage 与之前缩略图对应的关闭动画
     */
    Animator dismissAnimator(View beforeView, Location afterView);

    /**
     * 创建点击关闭 TransferImage 与之前缩略图不能对应的动画
     *
     * @param beforeView TransferImage 中当前所显示的图片对象
     * @return TransferImage 不能与之前缩略图对应的关闭动画
     */
    Animator dismissMissAnimator(View beforeView);


    /**
     * 背景透明动画
     * @param parent 背景
     * @return
     */
    Animator dismissBackgroundAnimator(View parent);
}
