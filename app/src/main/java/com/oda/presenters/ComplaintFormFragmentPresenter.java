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

    public boolean isComplaintValid(Complaint complaint){
        String missingInfo = "Hace falta informacion";
        if(complaint.getInformantName().equals("")){
            view.displayMessage(missingInfo, "Nombre no puede estar vacio");
            return false;
        }
        if(complaint.getInformantLastName().equals("")){
            view.displayMessage(missingInfo, "Apellido no puede estar vacio");
            return false;
        }
        if(complaint.getInformantLastName().equals("")){
            view.displayMessage(missingInfo, "Por favor describe la situacion");
            return false;
        }
        if(complaint.getAddress().equals("")  && complaint.getLatitude().equals("")){
            view.displayMessage(missingInfo, "Ingresa la direccion o localizala en el mapa");
            return false;
        }
        return true;
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
