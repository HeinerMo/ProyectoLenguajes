package com.lang.proyectolenguajes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.model.LoginModel;
import com.lang.proyectolenguajes.viewmodel.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private String imgSrc = "http://susoca-001-site1.dtempurl.com/Img/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        LinearLayout linearLayout = findViewById(R.id.summaryLayout);
        ArrayList<Event> events = new EventModel().getStudentEvents(new LoginModel().getStudent().getId());
        //create
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int id;

        LayoutInflater inflater = LayoutInflater.from(SummaryActivity.this);


        for (Event e : events) {
            if (e.getAvaiblePlaces() > 0) {
                id = R.layout.fragment_event_remove;
                final ConstraintLayout layout = (ConstraintLayout) inflater.inflate(id, null, false);
                TextView txt = (TextView) layout.getViewById(R.id.txtEventTitle);
                txt.setText(e.getEventName());

                txt = (TextView) layout.getViewById(R.id.txtEventInfo);
                txt.setText(e.getArtist().getName());
                ImageView img = (ImageView) layout.getViewById(R.id.imgEventAdd);
                String url = imgSrc + e.getArtist().getImgName();
                loadImage(url, img);


                Button button = (Button) layout.getViewById(R.id.btnRemoveSubscription);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(SummaryActivity.this);
                        alert.setTitle("Eliminar");
                        alert.setMessage("¿De verdad quieres cancelar tu asistencia a este evento?");
                        alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new EventModel().signOffEvent(new LoginModel().getStudent().getId(), e.getEventId());
                                linearLayout.removeView(layout);
                            }
                        });

                        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert.show();
                    }
                });
                linearLayout.addView(layout);
                id = R.layout.separator;
                ConstraintLayout cl = (ConstraintLayout) inflater.inflate(id, null, false);
                linearLayout.addView(cl);
            }
        }
    }

    private void loadImage(String url, ImageView view) {
        Picasso.get().load(url).into(view);
    }

}