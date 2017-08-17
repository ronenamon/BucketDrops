package com.ronentech.ronen.bucketdrops;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ronentech.ronen.bucketdrops.beans.Drop;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ronen on 17/08/2017.
 */

public class DialogAdd extends DialogFragment {

    private ImageButton mBtnClose;
    private EditText mInputWhat;
    private DatePicker mInputWhen;
    private Button mBtnAdd;



    public DialogAdd() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnClose = view.findViewById(R.id.btn_close);
        mInputWhat = view.findViewById(R.id.et_drop);
        mInputWhen = view.findViewById(R.id.bpv_date);
        mBtnAdd = (Button) view.findViewById(R.id.btn_add_it);

        mBtnClose.setOnClickListener(mBtnClickListener);
        mBtnAdd.setOnClickListener(mBtnClickListener);


    }


    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.btn_add_it:
                    addAction();
                    Log.v("V"  , "AddAction");
                    dismiss();
                    break;
                case R.id.btn_close:
                    Log.v("V"  , "dismiss");
                    dismiss();
                    break;
            }
        }
    };


    //TODO proccess date
    private void addAction() {
        String what = mInputWhat.getText().toString();
        long now = System.currentTimeMillis();


        RealmConfiguration config =  new RealmConfiguration.Builder(getActivity()).build();
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        Drop drop = new Drop(what,now , 0 , false);

        realm.beginTransaction();
        realm.copyToRealm(drop);
        realm.commitTransaction();
        realm.close();

    }
}
