package com.oda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ComplaintRecordFragment extends Fragment {

    private Button complaintButton;

    private OnComplaintFragmentClickListener onComplaintFragmentClickListener;

    public interface OnComplaintFragmentClickListener{
        public void onComplaintRecord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_record, container, false);

        onComplaintFragmentClickListener = (OnComplaintFragmentClickListener) getActivity();

        complaintButton =view.findViewById(R.id.button_record_complaint);

        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComplaintFragmentClickListener.onComplaintRecord();
            }
        });

        return view;
    }
}
