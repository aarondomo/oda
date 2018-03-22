package com.oda.di;

import com.oda.model.AuthorizationManager;
import com.oda.model.FirebaseDbInteracter;
import com.oda.presenters.ComplaintFormFragmentPresenter;
import com.oda.presenters.ComplaintRecordFragmentPresenter;
import com.oda.presenters.CreateAccountFragmentPresenter;
import com.oda.presenters.LoginFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public ComplaintFormFragmentPresenter providesComplaintFormFragmentPresenter(){
        return new ComplaintFormFragmentPresenter();
    }

    @Provides
    public FirebaseDbInteracter providesFirebaseDbInteracter(){
        return new FirebaseDbInteracter();
    }

    @Provides
    public ComplaintRecordFragmentPresenter providesComplaintRecordFragmentPresenter(){
        return new ComplaintRecordFragmentPresenter();
    }
    @Provides
    public CreateAccountFragmentPresenter providesCreateAccountFragmentPresenter(){
        return new CreateAccountFragmentPresenter();
    }

    @Provides
    public LoginFragmentPresenter providesLoginFragmentPresenter(){
        return new LoginFragmentPresenter();
    }

    @Provides
    public AuthorizationManager providesAuthorizationManager(){
        return new AuthorizationManager();
    }

}
