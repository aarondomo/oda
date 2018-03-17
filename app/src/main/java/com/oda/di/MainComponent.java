package com.oda.di;

import com.oda.ui.ComplaintFormFragment;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(ComplaintFormFragment complaintFormFragment);
}
