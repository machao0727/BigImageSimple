package com.mc.bigimagelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mc.libray.ImageLoader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void groupImage(View view){
        startActivity(new Intent(this,GroupImageActivity.class));
    }

    public void more(View view){
        startActivity(new Intent(this,ImagesActivity.class));
    }
}
