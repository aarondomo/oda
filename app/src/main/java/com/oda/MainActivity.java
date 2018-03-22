package com.oda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oda.R;
import com.oda.model.Complaint;
import com.oda.ui.ComplaintsActivity;
import com.oda.ui.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, ComplaintsActivity.class));
    }
}
