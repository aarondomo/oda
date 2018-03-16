package com.oda.oda.presenters;


public class LoginActivityPresenter {

    public interface View {

    }

    private View view;

    public void attachView(View view){
        this.view = view;
    }

    public void dettachView(){
        this.view = null;
    }

}
