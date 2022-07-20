package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.model.LoginModel;
import com.lang.proyectolenguajes.viewmodel.Event;

public class EventInfoActivity extends AppCompatActivity {

    private Button btnSign;
    private TextView txtName;
    private TextView txtInfo;
    private Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        btnSign = findViewById(R.id.btnSignToEvent);

        txtInfo = findViewById(R.id.txtEventInfoActivity);
        txtName = findViewById(R.id.txtEventActivityName);


        int eventId;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            eventId = -1;
        } else {
            eventId = extras.getInt("eventID");
        }
        e = null;
        if (eventId != -1) {
            e = new EventModel().getEvent(eventId);
            if (e != null) {
                txtName.setText(e.getEventName());
                String s = "Artista: "
                        + e.getArtist().getName() + "\n\n"
                        + "Canción: " + e.getArtist().getSong() + "\n\n"
                        + "Recinto: " + e.getCampusName() + "\n\n"
                        + "Fecha apertura: " + e.getStartingDate() + "\n\n"
                        + "Fecha Cierre: " + e.getClosingDate() + "\n\n"
                        + "Espacios Totales: " + e.getPlaces() + "\n\n"
                        + "Espacios disponibles: " + e.getAvaiblePlaces();
                txtInfo.setText(s);
            }
        }

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e != null) {
                    String result = new EventModel().signToEvent(new LoginModel().getStudent().getId(), "" + e.getEventId());
                    switch (result) {
                        case "reduntant":
                            Toast.makeText(EventInfoActivity.this, "¡Ya estas registrado a este evento!", Toast.LENGTH_SHORT).show();
                            break;
                        case "success":
                            Toast.makeText(EventInfoActivity.this, "¡Listo!", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        case "places":
                            Toast.makeText(EventInfoActivity.this, "¡Vaya!, parece que te ganaron el espacio.", Toast.LENGTH_SHORT).show();
                            char[] text = txtInfo.getText().toString().toCharArray();
                            text[text.length - 1] = '0';
                            txtInfo.setText(String.copyValueOf(text));
                            break;
                        default:
                            Toast.makeText(EventInfoActivity.this, "Algo salió mal..." + result, Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Toast.makeText(EventInfoActivity.this, "else", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}