package com.ronentech.ronen.bucketdrops;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

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
        mBtnAdd = view.findViewById(R.id.btn_add_it);

        mBtnClose.setOnClickListener(mBtnCloseListener);

    }


    private View.OnClickListener mBtnCloseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };
}
