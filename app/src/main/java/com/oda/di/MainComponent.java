package com.oda.di;

import com.oda.ui.ComplaintRecordFragment;
import com.oda.presenters.BasePresenter;
import com.oda.ui.ComplaintFormFragment;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(ComplaintFormFragment complaintFormFragment);

    void inject(ComplaintRecordFragment complaintRecordFragment);

    void inject(BasePresenter basePresenter);
}
