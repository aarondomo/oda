package com.oda.oda.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oda.oda.R;
import com.oda.oda.presenters.LoginActivityPresenter;

public class LoginActivity extends AppCompatActivity implements LoginActivityPresenter.View {

    //TODO: inject presenter
    LoginActivityPresenter presenter = new LoginActivityPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettachView();
    }
}
