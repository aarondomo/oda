package com.oda.presenters;

import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.model.AuthorizationManager;
import com.oda.model.FirebaseDbInteracter;

import javax.inject.Inject;

public abstract class BasePresenter {

    @Inject
    FirebaseDbInteracter firebaseDbInteracter;

    @Inject
    AuthorizationManager authorizationManager;

    public BasePresenter() {
        setUpDagger();
    }


    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }
}
