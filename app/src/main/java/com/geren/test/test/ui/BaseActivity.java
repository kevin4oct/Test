package com.geren.test.test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geren.test.test.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
