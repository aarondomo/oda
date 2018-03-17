package com.oda.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oda.R;
import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.presenters.ComplaintFormFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import javax.inject.Inject;

public class ComplaintFormFragment extends Fragment implements ComplaintFormFragmentPresenter.View {

    @Inject
    ComplaintFormFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_form, container, false);

        setUpDagger();

        presenter.attachView(this);

        return view;
    }

    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dettachView();
    }

    @Override
    public void displayMessage(String title, String message) {
        AlertDialogBuilder.createNeutralAlertDialog(getContext(), title, message);
    }
}
