package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.viewmodel.Event;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        int amount = 10;
        LinearLayout linearLayout = findViewById(R.id.eventsLayout);

        ArrayList<Event>events = new EventModel().getEvents();
        //create
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int id;

        LayoutInflater inflater = LayoutInflater.from(EventListActivity.this);
        ConstraintLayout layout;

        for (Event e: events) {
            id = R.layout.fragment_event;
            layout = (ConstraintLayout) inflater.inflate(id, null, false);
            TextView txt = (TextView) layout.getViewById(R.id.txtEventTitle);
            txt.setText(e.getEventName());
            Button button = (Button) layout.getViewById(R.id.btnMoreInfo);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventListActivity.this, EventInfoActivity.class);
                    intent.putExtra("eventID", e.getEventId());
                    startActivity(intent);
                }
            });

            linearLayout.addView(layout);
            id = R.layout.separator;
            layout = (ConstraintLayout) inflater.inflate(id, null, false);
            linearLayout.addView(layout);
        }
    }
}