package com.ronentech.ronen.bucketdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ronentech.ronen.bucketdrops.adapters.AdapterDrops;
import com.ronentech.ronen.bucketdrops.beans.Drop;
import com.ronentech.ronen.bucketdrops.widgets.BucketRecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {
    //Toolbar v7
    Toolbar mToolbar;
    Button mButton;
    BucketRecyclerView mRecyclerView;
    Realm mRealm;
    RealmResults<Drop> mResults;
    View mEmptyView;
    AdapterDrops mAdapter;



    //for changes
    private RealmChangeListener mChangeListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            Log.d("Ronen" , "onChange was called");
            mAdapter.update(mResults);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        //query
        mResults=mRealm.where(Drop.class).findAllAsync();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButton = (Button)findViewById(R.id.btn_add);
        mEmptyView = findViewById(R.id.empty_drops);

        //add a drop button on the main screen
        mButton.setOnClickListener(this);


        //connection toe th xml recyclerView
        mRecyclerView=  (BucketRecyclerView) findViewById(R.id.rv_drops);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.hideIfEmpty(mToolbar);
        mRecyclerView.showIfEmpty(mEmptyView);
        mAdapter = new AdapterDrops(this , mResults);
        mRecyclerView.setAdapter(mAdapter);



        setSupportActionBar(mToolbar);
        initBackgroundImage();


    }//end

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

    @Override
    protected void onStart() {
        super.onStart();
        mResults.addChangeListener(mChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListener(mChangeListener);
    }
}
