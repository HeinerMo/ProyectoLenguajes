package com.lang.proyectolenguajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lang.proyectolenguajes.model.EventModel;
import com.lang.proyectolenguajes.model.LoginModel;
import com.lang.proyectolenguajes.viewmodel.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecommendedActivity extends AppCompatActivity {

    private String imgSrc = "http://susoca-001-site1.dtempurl.com/Img/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        LinearLayout linearLayout = findViewById(R.id.recommendedLayout);

        ArrayList<Event> events = new EventModel().getEvents(new LoginModel().getStudent().getCampus().getId());
        //create
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int id;

        LayoutInflater inflater = LayoutInflater.from(RecommendedActivity.this);
        ConstraintLayout layout;

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
                        Intent intent = new Intent(RecommendedActivity.this, EventInfoActivity.class);
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

    private void loadImage(String url, ImageView view) {
        Picasso.get().load(url).into(view);
    }

}