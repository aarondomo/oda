package com.oda.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
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
    private EditText editTextInformantName;
    private EditText editTextInformantLastName;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextSituation;
    private OnShowMap onShowMap;
    private Button buttonLocationAddress;

    private LatLng latLng;

    EditText editTextAddress;

    public interface OnShowMap{
        public void showMap();
    }

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
        buttonLocationAddress = view.findViewById(R.id.button_location_address);

        setOnClickListeners();

        setUpDagger();

        presenter.attachView(this);

        return view;
    }


    private void setOnClickListeners(){
        buttonLocationAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnShowMap)getActivity()).showMap();
            }
        });


        sendComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                presenter.sendComplaint(new Complaint(getStringFromEditText(editTextInformantName),
                                getStringFromEditText(editTextInformantLastName),
                                getStringFromEditText(editTextName),
                                getStringFromEditText(editTextLastName),
                                getStringFromEditText(editTextSituation),
                                getStringFromEditText(editTextAddress),
                                getLatitude(),
                                getLongitude(),
                                "imageurl//url",
                                new Date().toString(),
                                "En proceso"),
                        getString(R.string.complaint_form_saving_success));
            }
        });
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

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    private String getLatitude() {
        if(latLng == null){
            return "";
        } else {
            return Double.toString(latLng.latitude);
        }
    }

    private String getLongitude() {
        if(latLng == null){
            return "";
        } else {
            return Double.toString(latLng.longitude);
        }
    }
}
