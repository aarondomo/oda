package com.oda.di;

import com.oda.model.FirebaseDbInteracter;
import com.oda.presenters.ComplaintFormFragmentPresenter;
import com.oda.presenters.ComplaintRecordFragmentPresenter;

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

}
