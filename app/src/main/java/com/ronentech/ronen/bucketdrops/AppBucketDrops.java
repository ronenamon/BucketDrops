package com.ronentech.ronen.bucketdrops;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ronen on 20/08/2017.
 */

public class AppBucketDrops extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config =  new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);

    }
}
