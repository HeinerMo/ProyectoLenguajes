package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.LoginModel;

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

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
    }


    private void login() {
        if (checkFields()) {
            LoginModel loginModel = new LoginModel();
            String result = loginModel.login(txtUserName.getText().toString(), txtPassword.getText().toString());

            switch (result) {
                case "success":
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    break;
                case "password":
                        txtPassword.setError("La contraseña no es correcta");
                    break;
                case "username":
                        txtUserName.setError("El nombre de usuario no es correcto");
                    break;
            }
        }
    }

    private void signup() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }





    private boolean checkFields() {
        boolean output = true;
        if (txtPassword.getText().toString().length() < 4) {
            txtPassword.setError("Debe ser de por lo menos 4 dígitos");
            output = false;
        }
        if (txtUserName.getText().toString().length() < 4) {
            output = false;
            txtUserName.setError("Debe ingresar un nombre de usuario válido");
        }

        return output;
    }
}