package com.oda.presenters;

public abstract class BasePresenter {

    public interface View {
        public void displayMessage(String title, String message);
    }

    protected View view;

    public void attachView(View view){
        this.view = view;
    }
    public void dettachView(){
        this.view = null;
    }

}
