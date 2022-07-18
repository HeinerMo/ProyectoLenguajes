package com.lang.proyectolenguajes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.R;
import com.lang.proyectolenguajes.model.SignupModel;

public class SignupActivity extends AppCompatActivity {

    private Button signup, cancel;
    private TextView username, password, passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signup = findViewById(R.id.btnSignupSignupActivity);
        cancel = findViewById(R.id.btnCancelSignupActivity);

        username = findViewById(R.id.txtUsernameSignupActivity);
        password = findViewById(R.id.txtPasswordSignupActivity);
        passwordCheck = findViewById(R.id.txtPasswordConfirmationSignupActivity);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }

    private void cancel() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private String result;
    private void signup() {
        if (checkFields() && checkPasswordConfirmation()) {
            SignupModel signupModel = new SignupModel();

            result = signupModel.createUser(username.getText().toString(), password.getText().toString());


            if (result.equalsIgnoreCase("Success")) {
                Intent intent = new Intent(SignupActivity.this, SuccessActivity.class);
                startActivity(intent);
            } else if (result.equalsIgnoreCase("username")) {
                username.setError("Este nombre no se encuentra disponible");
            } else {
                Toast.makeText(SignupActivity.this, result, Toast.LENGTH_LONG).show();
            }
        } else {
        }
    }

    private boolean checkPasswordConfirmation() {
        if (password.getText().toString().equals(passwordCheck.getText().toString())) {
            return true;
        } else {
            password.setError("Las contraseñas debe coincidir.");
            passwordCheck.setError("Las contraseñas debe coincidir.");
            return false;
        }
    }

    private boolean checkFields() {
        boolean output = true;
        if (password.getText().toString().length() < 4) {
            password.setError("Debe ser de por lo menos 4 dígitos");
            output = false;
        }
        if (passwordCheck.getText().toString().length() < 4) {
            output = false;
            passwordCheck.setError("Debe ser de por lo menos 4 dígitos");
        }
        if (username.getText().toString().length() < 4) {
            output = false;
            username.setError("Debe ingresar un nombre de usuario válido");
        }

        return output;
    }

}