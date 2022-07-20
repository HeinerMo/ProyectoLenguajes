package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.viewmodel.Event;

public class EventInfoActivity extends AppCompatActivity {

    private Button btnSign;
    private TextView txtName;
    private TextView txtInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        btnSign = findViewById(R.id.btnSignToEvent);

        txtInfo = findViewById(R.id.txtEventInfoActivity);
        txtName = findViewById(R.id.txtEventActivityName);


        int eventId;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            eventId = -1;
        } else {
            eventId= extras.getInt("eventID");
        }

        Log.wtf("lol", "" + eventId);
        if (eventId != -1) {
            Event e = new EventModel().getEvent(eventId);
            if (e != null) {
                txtName.setText(e.getEventName());
                String s = "Artista: "
                        + e.getArtist().getName() + "\n\n"
                        + "Canción: " + e.getArtist().getSong() + "\n\n"
                        + "Recinto: " + e.getCampusName() + "\n\n"
                        + "Fecha apertura: " + e.getStartingDate() + "\n\n"
                        + "Fecha Cierre: " + e.getClosingDate() + "\n\n"
                        + "Espacios disponibles: " + e.getPlaces();
                txtInfo.setText(s);
            }
        }

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventInfoActivity.this, "YUP", Toast.LENGTH_SHORT).show();
            }
        });

    }
}