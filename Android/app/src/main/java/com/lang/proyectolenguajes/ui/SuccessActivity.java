package com.lang.proyectolenguajes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lang.proyectolenguajes.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        if (getIntent().getStringExtra("message") != null) {

        }
    }
}