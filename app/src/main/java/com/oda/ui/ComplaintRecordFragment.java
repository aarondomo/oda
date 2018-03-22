package com.oda.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.oda.R;
import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.model.Complaint;
import com.oda.presenters.ComplaintRecordFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import java.util.List;

import javax.inject.Inject;

public class ComplaintRecordFragment extends Fragment implements ComplaintRecordFragmentPresenter.View {

    @Inject
    ComplaintRecordFragmentPresenter presenter;

    private Button complaintButton;

    private OnComplaintFragmentClickListener onComplaintFragmentClickListener;
    private TableLayout tableLayouyComplaints;

    public interface OnComplaintFragmentClickListener{
        public void onComplaintRecord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_record, container, false);

        setUpDagger();

        presenter.attachView(this);

        onComplaintFragmentClickListener = (OnComplaintFragmentClickListener) getActivity();

        complaintButton = view.findViewById(R.id.button_record_complaint);
        tableLayouyComplaints = view.findViewById(R.id.tableLayout_display_complaints);

        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComplaintFragmentClickListener.onComplaintRecord();
            }
        });

        presenter.retrieveComplaints();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dettachView();
    }

    @Override
    public void displayComplaints(final List<Complaint> complaints) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Context context = getContext();

                TableRow tableRow = createTableRow(context,
                                                getString(R.string.table_record_header_date),
                                                getString(R.string.table_record_header_situation),
                                                getString(R.string.table_record_header_status));

                tableLayouyComplaints.addView(tableRow);

                for(final Complaint complaint : complaints) {

                    tableRow = createTableRow(context,
                            complaint.getDate(),
                            complaint.getSituation(),
                            complaint.getStatus());

                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialogBuilder.createNeutralAlertDialog(context, complaint.getStatus(), complaint.getComplaintStatusDescription());
                        }
                    });

                    tableLayouyComplaints.addView(tableRow);
                }
                tableLayouyComplaints.setShrinkAllColumns(true);
            }
        });
    }


    private TableRow createTableRow(Context context,
                                    String... texts){
        TableRow tableRow = new TableRow(context);

        for(String text : texts){
            TextView textView = new TextView(context);
            textView.setText(text);
            tableRow.addView(textView);
        }

        return tableRow;
    }

    @Override
    public void displayMessage(String title, String message) {

    }

    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }
}
