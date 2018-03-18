package com.oda.presenters;

import com.oda.model.Complaint;


public class ComplaintFormFragmentPresenter extends BasePresenter {

    public interface View extends BaseView{
    }

    private View view;

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

}
