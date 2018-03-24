package com.oda.presenters;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.storage.StorageReference;
import com.oda.model.Complaint;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ComplaintFormFragmentPresenter extends BasePresenter {

    public interface View extends BaseView{

    }

    private View view;
    private StringBuffer multimediaFiles = new StringBuffer().append("");

    private LatLng latLng;

    public void attachView(View view){
        this.view = view;
    }

    public void dettachView(){
        this.view = null;
    }

    public ComplaintFormFragmentPresenter() {
    }

    public void sendComplaint(Complaint complaint, String confirmationText){
        firebaseDbInteracter.addComplaint(complaint);
        view.displayMessage("Hecho", confirmationText);
    }

    public String getMultimediafiles(){
        return new String(multimediaFiles);
    }


    public String getFormattedTodaysDate(){
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        String date = DATE_FORMAT.format(new Date());
        return date;
    }

    public void addMultimediaString(StorageReference storageReference) {
        multimediaFiles.append(storageReference);
        multimediaFiles.append(",");
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getLatitude() {
        if(latLng == null){
            return "";
        } else {
            return Double.toString(latLng.latitude);
        }
    }

    public String getLongitude() {
        if(latLng == null){
            return "";
        } else {
            return Double.toString(latLng.longitude);
        }
    }
}
