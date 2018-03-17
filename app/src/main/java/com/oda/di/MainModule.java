package com.oda.di;

import com.oda.presenters.ComplaintFormFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public ComplaintFormFragmentPresenter providesComplaintFormFragmentPresenter(){
        return new ComplaintFormFragmentPresenter();
    }
}
