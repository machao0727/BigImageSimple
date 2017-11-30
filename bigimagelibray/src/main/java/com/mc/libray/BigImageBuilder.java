package com.mc.libray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MC on 2017/11/28.
 * builder
 */

public class BigImageBuilder implements Parcelable {
    private List<String> images;//图片地址集合
    private List<String> placeHolderUrl;//缩略图地址集合
    private List<Location> placeHolderLocation=new ArrayList<>();//占位图坐标信息
    private int currentIndex;//选中图片位置
    private int startPosition=0;//针对listview,recyclerview等列表需要知道页面中开始的第一个position
    private Activity mContext;


    public BigImageBuilder(Activity context) {
        mContext = context;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public BigImageBuilder setStartPosition(int startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public BigImageBuilder setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public BigImageBuilder setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    public List<String> getPlaceHolderUrl() {
        return placeHolderUrl;
    }

    public BigImageBuilder setPlaceHolderUrl(List<String> placeHolderUrl) {
        this.placeHolderUrl = placeHolderUrl;
        return this;
    }

    public List<Location> getPlaceHolderLocation() {
        return placeHolderLocation;
    }


    public BigImageBuilder setOldImage(List<ImageView> oldImage) {
        for (int i = 0; i < oldImage.size(); i++) {
            getLocation(oldImage.get(i));
        }
        return this;
    }
    public void show() {
        Intent intent = new Intent(mContext, BigImageActivity.class);
        intent.putExtra("builder",this);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(0,0);
    }
    private void getLocation(ImageView view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Location oLocation = new Location();
        oLocation.setX(location[0]);
        oLocation.setY(location[1]);
        oLocation.setWidth(view.getWidth());
        oLocation.setHeight(view.getHeight());
        placeHolderLocation.add(oLocation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.images);
        dest.writeStringList(this.placeHolderUrl);
        dest.writeTypedList(this.placeHolderLocation);
        dest.writeInt(this.currentIndex);
        dest.writeInt(this.startPosition);
    }

    protected BigImageBuilder(Parcel in) {
        this.images = in.createStringArrayList();
        this.placeHolderUrl = in.createStringArrayList();
        this.placeHolderLocation = in.createTypedArrayList(Location.CREATOR);
        this.currentIndex = in.readInt();
        this.startPosition = in.readInt();
    }

    public static final Parcelable.Creator<BigImageBuilder> CREATOR = new Parcelable.Creator<BigImageBuilder>() {
        @Override
        public BigImageBuilder createFromParcel(Parcel source) {
            return new BigImageBuilder(source);
        }

        @Override
        public BigImageBuilder[] newArray(int size) {
            return new BigImageBuilder[size];
        }
    };
}
