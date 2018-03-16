package com.oda.oda.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.oda.oda.R;
import com.oda.oda.presenters.LoginActivityPresenter;

import static com.oda.oda.ui.LoginOptionFragment.CREATE_ACCOUNT;
import static com.oda.oda.ui.LoginOptionFragment.LOGIN_ACCOUNT;

public class LoginActivity extends AppCompatActivity implements LoginActivityPresenter.View, LoginOptionFragment.OnOptionSelectedListener {

    //TODO: inject presenter
    LoginActivityPresenter presenter = new LoginActivityPresenter();

    private FrameLayout frameLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frameLayout = findViewById(R.id.fragment_login_container);

        presenter.attachView(this);

        fragmentManager = getSupportFragmentManager();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (frameLayout != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new LoginOptionFragment to be placed in the activity layout
            LoginOptionFragment firstFragment = new LoginOptionFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_login_container, firstFragment)
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettachView();
    }

    @Override
    public void optionSelected(int option) {
        switch (option){

            //TODO: As these fragmentes share a lot of functionality and display,
            //create an abstraction to share layout and functionality
            case LOGIN_ACCOUNT:
                fragmentManager.beginTransaction()
                        .replace(frameLayout.getId(), new LoginFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case CREATE_ACCOUNT:
                fragmentManager.beginTransaction()
                        .replace(frameLayout.getId(), new CreateAccountFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

}
