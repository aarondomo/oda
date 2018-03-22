package com.oda.ui;


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
import com.oda.presenters.LoginFragmentPresenter;
import com.oda.utils.AlertDialogBuilder;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements LoginFragmentPresenter.View {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginAccountButton;

    @Inject
    LoginFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = view.findViewById(R.id.editText_create_account_username);
        passwordEditText = view.findViewById(R.id.editText_create_account_password);
        loginAccountButton = view.findViewById(R.id.button_create_account_start_session);

        setUpDagger();

        presenter.attachView(this);

        loginAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                presenter.loginToAccount(username, password);
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
    public void displayMessage(String title, String message) {
        AlertDialogBuilder.createNeutralAlertDialog(getContext(), title, message);
    }

    @Override
    public void goToHomeScreen() {
        Intent intent = new Intent(getActivity(), ComplaintsActivity.class);
        startActivity(intent);
    }

    private void setUpDagger(){
        DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }
}
