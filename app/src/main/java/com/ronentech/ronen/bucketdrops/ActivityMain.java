package com.ronentech.ronen.bucketdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ronentech.ronen.bucketdrops.adapters.AdapterDrops;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {



    //Toolbar v7
    Toolbar mToolbar;

    Button mButton;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButton = (Button)findViewById(R.id.btn_add);

        //add a drop button on the main screen
        mButton.setOnClickListener(this);


        setSupportActionBar(mToolbar);
        initBackgroundImage();

        //connection toe th xml recyclerView
        mRecyclerView=  (RecyclerView) findViewById(R.id.rv_drops);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new AdapterDrops(this));


    }

    //set the background by the Glide plugin
    private void initBackgroundImage() {
        ImageView BackgroundIV = (ImageView) findViewById(R.id.iv_background);
        //Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
        Glide.with(this).load(R.drawable.background).centerCrop().into(BackgroundIV);
    }


    @Override
    public void onClick(View view) {
        showDialogAdd();
    }

    private void showDialogAdd() {
        //create an objcet
        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(),"Add");

    }
}
