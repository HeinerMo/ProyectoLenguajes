package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.viewmodel.Event;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private String imgSrc = "http://susoca-001-site1.dtempurl.com/Img/";
    private ConstraintLayout layout;
    private ArrayList<Event> events;
    private LinearLayout.LayoutParams params;
    private LinearLayout  linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        linearLayout = findViewById(R.id.eventsLayout);
        loadLayout();
    }

    private void loadLayout() {

        events = new EventModel().getEvents();
        //create
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int id;

        LayoutInflater inflater = LayoutInflater.from(EventListActivity.this);

        //search bar
        id = R.layout.fragment_search;
        layout = (ConstraintLayout) inflater.inflate(id, null, false);
        EditText editText = (EditText) layout.getViewById(R.id.editTextEventNameSearch);
        Button btnSearch = (Button) layout.getViewById(R.id.btnEventSearch);
        linearLayout.addView(layout);

        id = R.layout.separator;
        layout = (ConstraintLayout) inflater.inflate(id, null, false);
        linearLayout.addView(layout);

        //events
        for (Event e : events) {
            if (e.getAvaiblePlaces() > 0) {
                id = R.layout.fragment_event_add;
                layout = (ConstraintLayout) inflater.inflate(id, null, false);
                TextView txt = (TextView) layout.getViewById(R.id.txtEventTitle);
                txt.setText(e.getEventName());

                txt = (TextView) layout.getViewById(R.id.txtEventInfo);
                txt.setText(e.getArtist().getName());

                ImageView img = (ImageView) layout.getViewById(R.id.imgEventAdd);
                String url = imgSrc + e.getArtist().getImgName();
                loadImage(url, img);


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


        //Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Debe ingresar un criterio de búsqueda");
                } else {
                    linearLayout.removeAllViews();
                    loadLayout(editText.getText().toString());
                    Toast.makeText(EventListActivity.this, "Buscando...", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void loadLayout(String term) {

        events = new EventModel().getEvents();
        //create
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int id;

        LayoutInflater inflater = LayoutInflater.from(EventListActivity.this);

        //search bar
        id = R.layout.fragment_search;
        layout = (ConstraintLayout) inflater.inflate(id, null, false);
        EditText editText = (EditText) layout.getViewById(R.id.editTextEventNameSearch);
        Button btnSearch = (Button) layout.getViewById(R.id.btnEventSearch);
        linearLayout.addView(layout);

        id = R.layout.separator;
        layout = (ConstraintLayout) inflater.inflate(id, null, false);
        linearLayout.addView(layout);

        //events
        for (Event e : events) {
            if (e.getAvaiblePlaces() > 0 && e.getEventName().toLowerCase().contains(term.toLowerCase())) {
                id = R.layout.fragment_event_add;
                layout = (ConstraintLayout) inflater.inflate(id, null, false);
                TextView txt = (TextView) layout.getViewById(R.id.txtEventTitle);
                txt.setText(e.getEventName());

                txt = (TextView) layout.getViewById(R.id.txtEventInfo);
                txt.setText(e.getArtist().getName());

                ImageView img = (ImageView) layout.getViewById(R.id.imgEventAdd);
                String url = imgSrc + e.getArtist().getImgName();
                loadImage(url, img);


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

        //Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Debe ingresar un criterio de búsqueda");
                } else {
                    linearLayout.removeAllViews();
                    loadLayout(editText.getText().toString());
                    Toast.makeText(EventListActivity.this, "Buscando...", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void loadImage(String url, ImageView view) {
        Picasso.get().load(url).into(view);
    }


}