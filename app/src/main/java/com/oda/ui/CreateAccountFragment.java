package com.oda.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oda.R;
import com.oda.di.DaggerMainComponent;
import com.oda.di.MainModule;
import com.oda.presenters.CreateAccountFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import javax.inject.Inject;


public class CreateAccountFragment extends Fragment implements CreateAccountFragmentPresenter.View {

    @Inject
    CreateAccountFragmentPresenter presenter;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button createAccountButton;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);


        usernameEditText = view.findViewById(R.id.editText_create_account_username);
        passwordEditText = view.findViewById(R.id.editText_create_account_password);
        createAccountButton = view.findViewById(R.id.button_create_account_start_session);

        progressDialog = new ProgressDialog(getContext());

        setUpDagger();

        presenter.attachView(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                presenter.createAccount(username, password);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dettachView();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void goToHomeScreen(){
        Intent intent = new Intent(getActivity(), ComplaintsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void displayMessage(String title, String message) {
        AlertDialogBuilder.createNeutralAlertDialog(getContext(), title, message);
    }

    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }
}
