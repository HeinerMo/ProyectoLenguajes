package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //ponerla siempre para ocultar el action bar.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}