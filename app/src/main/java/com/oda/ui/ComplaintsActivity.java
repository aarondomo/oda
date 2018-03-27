package com.oda.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.oda.R;

public class ComplaintsActivity extends AppCompatActivity implements ComplaintRecordFragment.OnComplaintFragmentClickListener,
                                                            GoogleMapFragment.OnLocationSelected, ComplaintFormFragment.OnShowMap {

    private int frameLayoutId;
    private FragmentManager fragmentManager;

    private ComplaintFormFragment complaintFormFragment;

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
                .replace(frameLayoutId, new ComplaintRecordFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onComplaintRecord() {
        complaintFormFragment = new ComplaintFormFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayoutId, complaintFormFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void saveLocation(LatLng coordinates) {
        complaintFormFragment.setLatLng(coordinates);
        fragmentManager.popBackStack();
    }

    @Override
    public void showMap() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(frameLayoutId, new GoogleMapFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
