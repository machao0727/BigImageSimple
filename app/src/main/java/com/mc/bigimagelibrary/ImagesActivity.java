package com.mc.bigimagelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mc.libray.BigImageBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    private ImageView image_view1;
    private ImageView image_view2;
    private ImageView image_view3;

    private List<String> plackhoolder;
    private List<ImageView> oldImgs;

    {
        plackhoolder = new ArrayList<>();
        plackhoolder.add("http://img3.imgtn.bdimg.com/it/u=1607536993,2981543664&fm=27&gp=0.jpg");
        plackhoolder.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3926906797,2598459279&fm=27&gp=0.jpg");
        plackhoolder.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1647664831,2813705788&fm=27&gp=0.jpg");
    }

    private List<String> imageList;

    {
        imageList = new ArrayList<>();
        imageList.add("http://pic30.nipic.com/20130519/12617486_164145320144_2.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512020804897&di=2d6676de7429c7919e3ad8d40fb1df08&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2Fdb940ffb277a8fc2a9b9d0d0cbf3169c8e4edeb1.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512020859571&di=e5f6efa61fa55436583d04dc3a09072b&imgtype=0&src=http%3A%2F%2Fgame.ahgame.com%2Fuploads%2Fallimg%2F160524%2F1-160524144S4.jpg");
        imageList.add("http://pic20.nipic.com/20120427/1717533_182452619167_2.jpg");
        imageList.add("http://pic21.photophoto.cn/20111115/0038038062396177_b.jpg");
        imageList.add("http://img1.3lian.com/2015/w19/99/d/81.jpg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        image_view1 = (ImageView) findViewById(R.id.image_view1);
        image_view2 = (ImageView) findViewById(R.id.image_view2);
        image_view3 = (ImageView) findViewById(R.id.image_view3);
        oldImgs = new ArrayList<>();
        oldImgs.add(image_view1);
        oldImgs.add(image_view2);
        oldImgs.add(image_view3);
        initView();
        initEvent();
    }

    private void initEvent() {
        image_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBigImage(0);
            }
        });
        image_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBigImage(1);
            }
        });
        image_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBigImage(2);
            }
        });
    }

    private void showBigImage(int i) {
        new BigImageBuilder(this).setImages(imageList)
                .setOldImage(oldImgs)
                .setCurrentIndex(i)
                .setPlaceHolderUrl(plackhoolder)
                .show();
    }

    private void initView() {
        Picasso.with(this).load(plackhoolder.get(0)).placeholder(R.mipmap.ic_launcher).into(image_view1);
        Picasso.with(this).load(plackhoolder.get(1)).placeholder(R.mipmap.ic_launcher).into(image_view2);
        Picasso.with(this).load(plackhoolder.get(2)).placeholder(R.mipmap.ic_launcher).into(image_view3);
    }
}
