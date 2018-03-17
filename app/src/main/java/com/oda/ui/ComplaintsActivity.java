package com.oda.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.oda.ComplaintRecordFragment;
import com.oda.R;

public class ComplaintsActivity extends AppCompatActivity implements ComplaintRecordFragment.OnComplaintFragmentClickListener {

    private int frameLayoutId;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        frameLayoutId = R.id.fragment_complaint_container;

        fragmentManager = getSupportFragmentManager();

        displayComplaintRecordFragment();

    }

    private void displayComplaintRecordFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(frameLayoutId, new ComplaintRecordFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onComplaintRecord() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayoutId, new ComplaintFormFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
