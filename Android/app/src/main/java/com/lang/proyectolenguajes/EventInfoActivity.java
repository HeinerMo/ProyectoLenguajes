package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.model.LoginModel;
import com.lang.proyectolenguajes.viewmodel.Event;

public class EventInfoActivity extends AppCompatActivity {

    private Button btnSign, btnAddComment;
    private TextView txtName;
    private TextView txtInfo;
    private Event e;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        btnSign = findViewById(R.id.btnSignToEvent);

        txtInfo = findViewById(R.id.txtEventInfoActivity);
        txtName = findViewById(R.id.txtEventActivityName);

        editTextComment = findViewById(R.id.editTxtEventInfoComment);
        btnAddComment = findViewById(R.id.btnEventInforComment);


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


        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if comment is empty

                if (editTextComment.getText().toString().isEmpty()) {
                    editTextComment.setError("Debe ingresar un comentario");
                } else {
                    String result = new EventModel().addComment(editTextComment.getText().toString(), e.getArtist().getId(), new LoginModel().getStudent().getId());
                    switch (result) {
                        case "success":
                            Toast.makeText(EventInfoActivity.this, "Se a agregado tu comentario.", Toast.LENGTH_LONG).show();
                            editTextComment.setText("");
                            break;
                        case "error":
                            Toast.makeText(EventInfoActivity.this, "¡Oh no! Algo ha salido mal.", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });
    }
}