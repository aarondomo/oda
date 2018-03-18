package com.oda.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oda.R;
import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.model.Complaint;
import com.oda.presenters.ComplaintFormFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import java.util.Date;
import java.util.UUID;

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
    private Button buttonAddImage;


    private LatLng latLng;

    private StringBuffer multimediaFiles = new StringBuffer().append("");

    EditText editTextAddress;
    private static final int INTENT_REQUEST_CODE = 123;

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
        buttonAddImage = view.findViewById(R.id.button_form_add_images);

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
                                new String(multimediaFiles),
                                new Date().toString(),
                                "En proceso"),
                        getString(R.string.complaint_form_saving_success));
            }
        });

        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryIntent();
            }
        });


    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.complaint_form_select_files)), INTENT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if(data.getClipData() != null) {
                int itemCount = data.getClipData().getItemCount();
                int currentItem = 0;
                for(int i = 0; i < itemCount; i++){
                    Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                    String downloadReference = uploadImage(imageUri);
                    multimediaFiles.append(downloadReference);
                    multimediaFiles.append(",");
                }
            }

        }
    }


    private String uploadImage(Uri filePath) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            FirebaseStorage storage= FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            storageReference = storageReference.child("images/complaints"+ UUID.randomUUID().toString());
            storageReference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
            return storageReference.toString();
        }
        return "";
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
