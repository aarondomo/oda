package com.oda.oda.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oda.oda.R;

public class LoginOptionFragment extends Fragment{

    public static final int CREATE_ACCOUNT = 1;
    public static final int LOGIN_ACCOUNT = 2;

    private OnOptionSelectedListener onOptionSelectedListener;

    private Button createAccountButton;
    private Button loginToAccountButton;


    // Container Activity must implement this interface
    public interface OnOptionSelectedListener {
        public void optionSelected(int option);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            onOptionSelectedListener = (OnOptionSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_options, container, false);

        bindViews(view);

        return view;
    }

    private void bindViews(View view){
        createAccountButton = view.findViewById(R.id.button_loginfragment_createaccount);
        loginToAccountButton = view.findViewById(R.id.button_loginfragment_loginaccount);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionSelectedListener.optionSelected(CREATE_ACCOUNT);
            }
        });

        loginToAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionSelectedListener.optionSelected(LOGIN_ACCOUNT);
            }
        });
    }

}
