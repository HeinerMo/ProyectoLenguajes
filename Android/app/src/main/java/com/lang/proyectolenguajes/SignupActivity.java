package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.SignupModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private Button signup;
    private TextView name, carnet, password, passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signup = findViewById(R.id.btnSignupSignupActivity);

        carnet = findViewById(R.id.txtCarnetSignupActivity);
        name = findViewById(R.id.txtNameSignupActiviy);
        password = findViewById(R.id.txtPasswordSignupActivity);
        passwordCheck = findViewById(R.id.txtPasswordConfirmationSignupActivity);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
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

            result = signupModel.createUser(carnet.getText().toString(), name.getText().toString(), password.getText().toString());


            if (result.equalsIgnoreCase("Success")) {
                Intent intent = new Intent(SignupActivity.this, SuccessActivity.class);
                startActivity(intent);
            } else if (result.equalsIgnoreCase("username")) {
                carnet.setError("Este nombre no se encuentra disponible");
            } else if (result.equalsIgnoreCase("carnet")) {
                carnet.setError("Este nombre no se encuentra disponible");
            } else {
                Toast.makeText(SignupActivity.this, result, Toast.LENGTH_LONG).show();
            }
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
        String s = carnet.getText().toString();
        // Compile regular expression
        final Pattern pattern = Pattern.compile("^[a-zA-Z]\\d\\d\\d\\d\\d$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(s);

        if (!matcher.matches()) {
            output = false;
            carnet.setError("El carné no es válido");
        }
        if (name.getText().toString().length() < 4) {
            name.setError("Debe ser de por lo menos 4 dígitos");
            output = false;
        }

        return output;
    }

}