package com.mc.libray;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MC on 2017/11/29.
 * imageview坐标
 */

public class Location implements Parcelable {
    private int X;
    private int Y;
    private int width;
    private int height;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.X);
        dest.writeInt(this.Y);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.X = in.readInt();
        this.Y = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
