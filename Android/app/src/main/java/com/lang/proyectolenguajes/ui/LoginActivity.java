package com.lang.proyectolenguajes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.R;
import com.lang.proyectolenguajes.data.SignupData;

public class LoginActivity extends AppCompatActivity {
    private Button signin, signup;
    private TextView txtUserName, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se necesita en toda la aplicación, no borrar ni mover más abajo.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        signin = findViewById(R.id.btnLoginLoginActivity);
        signup = findViewById(R.id.btnSignupLoginActivity);
        txtUserName = findViewById(R.id.txtUsernameLoginActivity);
        txtPassword = findViewById(R.id.txtPasswordLoginActivity);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }


    private void login() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        String s = SignupData.getInstance().test();
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
    }

    private void signup() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}