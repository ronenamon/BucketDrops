package com.ronentech.ronen.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ronentech.ronen.bucketdrops.R;
import com.ronentech.ronen.bucketdrops.beans.Drop;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by ronen on 17/08/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {

    private  LayoutInflater mInflater;
    private RealmResults<Drop> mResults;
    public static final String TAG = "Ronen";

    public AdapterDrops(Context context , RealmResults<Drop> results){

        mInflater = LayoutInflater.from(context);

       update(results);
    }

    //dummy data
//    public static ArrayList<String> generateValues(){
//        ArrayList<String> dummyValues = new ArrayList<>();
//        for (int i = 0 ; i<100;i++){
//            dummyValues.add("Item " + i );
//
//        }
//        return dummyValues;
//    }


    public void update(RealmResults<Drop> results){
        mResults = results;
        notifyDataSetChanged();//for updated items for ALL
    }

    @Override
    public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row_drop,parent,false);
        DropHolder holder = new DropHolder(view);
        Log.d(TAG,"onCreateViewHolder");
        return holder;

    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        Drop drop = mResults.get(position);
        holder.mTextWhat.setText(drop.getWhat());
        holder.mTextWhen.setText(drop.getAdded()+"");
        Log.d(TAG,"onBindViewHolder = "+position);


    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public static class DropHolder extends RecyclerView.ViewHolder{

        TextView mTextWhat;
        TextView mTextWhen;


        public DropHolder(View itemView) {
            super(itemView);
            mTextWhat = itemView.findViewById(R.id.tv_what);
            mTextWhen = itemView.findViewById(R.id.tv_when);

        }
    }


}
