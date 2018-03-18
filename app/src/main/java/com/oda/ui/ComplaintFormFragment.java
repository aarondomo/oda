package com.oda.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oda.R;
import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.model.Complaint;
import com.oda.presenters.ComplaintFormFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import java.util.Date;

import javax.inject.Inject;

public class ComplaintFormFragment extends Fragment implements ComplaintFormFragmentPresenter.View {

    @Inject
    ComplaintFormFragmentPresenter presenter;

    private Button sendComplaintButton;

    EditText editTextInformantName;
    EditText editTextInformantLastName;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextSituation;
    EditText editTextAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_form, container, false);

        sendComplaintButton = view.findViewById(R.id.button_complaint_form_send_complaint);
        editTextInformantName = view.findViewById(R.id.edittext_informant_name);
        editTextInformantLastName = view.findViewById(R.id.edittext_informant_last_name);
        editTextName = view.findViewById(R.id.edittext_name);
        editTextLastName = view.findViewById(R.id.edittext_last_name);
        editTextSituation = view.findViewById(R.id.edittext_situation);
        editTextAddress = view.findViewById(R.id.edittext_address);

        sendComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                presenter.sendComplaint(new Complaint(getStringFromEditText(editTextInformantName),
                        getStringFromEditText(editTextInformantLastName),
                        getStringFromEditText(editTextName),
                        getStringFromEditText(editTextLastName),
                        getStringFromEditText(editTextSituation),
                        getStringFromEditText(editTextAddress),
                        "12.0",
                        "11.0",
                        "imageurl//url",
                        new Date().toString(),
                        "En proceso"),
                        getString(R.string.complaint_form_saving_success));
            }
        });

        setUpDagger();

        presenter.attachView(this);

        return view;
    }

    private String getStringFromEditText(EditText editText){
        return editText.getText().toString();
    }

    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dettachView();
    }

    @Override
    public void displayMessage(String title, String message) {
        AlertDialogBuilder.createNeutralAlertDialogWithIcon(getContext(), message, R.drawable.image_placeholder);
        getFragmentManager().popBackStack();
    }
}
