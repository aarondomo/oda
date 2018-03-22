package com.oda.di;

import com.oda.ui.ComplaintRecordFragment;
import com.oda.presenters.BasePresenter;
import com.oda.ui.ComplaintFormFragment;
import com.oda.ui.CreateAccountFragment;
import com.oda.ui.LoginFragment;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(ComplaintFormFragment complaintFormFragment);

    void inject(ComplaintRecordFragment complaintRecordFragment);

    void inject(BasePresenter basePresenter);

    void inject(CreateAccountFragment createAccountFragment);

    void inject(LoginFragment loginFragment);

}
