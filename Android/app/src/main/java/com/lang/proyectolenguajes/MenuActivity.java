package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button btnEvents, btnRecomendations, btnSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnEvents = findViewById(R.id.btnEventsMenu);
        btnRecomendations = findViewById(R.id.btnRecomendedMenu);
        btnSummary = findViewById(R.id.btnSummaryMenu);

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, EventListActivity.class));
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SummaryActivity.class));
            }
        });

        btnRecomendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, RecommendedActivity.class));
            }
        });
    }
}