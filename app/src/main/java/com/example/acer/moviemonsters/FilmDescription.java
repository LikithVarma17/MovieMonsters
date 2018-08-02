package com.example.acer.moviemonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmDescription extends AppCompatActivity {
    TextView tv_data;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_description);
        JsonMovie j = (JsonMovie) getIntent().getExtras().get("x");
        iv_image = (ImageView) findViewById(R.id.front_poster);
        String s = j.getPoster_path();
        String s2 = j.getBackdrop_path();
        Picasso.with(FilmDescription.this).load(s).into(iv_image);
        ImageView iv_image2 = (ImageView) findViewById(R.id.back_poster);
        Picasso.with(FilmDescription.this).load("http://image.tmdb.org/t/p/w500" + s2 + "?api_key=78bdf0295b5959b8b7fc3b150628fcef").into(iv_image2);
        tv_data = (TextView) findViewById(R.id.id_title);
        tv_data.setText(j.getTitle());
        tv_data = (TextView) findViewById(R.id.rating);
        tv_data.setText("Rating : " + j.getVote_average());
        tv_data = (TextView) findViewById(R.id.votes);
        tv_data.setText("Public Votes : " + j.getVote_count());
        tv_data = (TextView) findViewById(R.id.release);
        tv_data.setText("Date Of Release : " + j.getRelease_date());
        tv_data = (TextView) findViewById(R.id.language);
        tv_data.setText(j.getOverview());
    }
}
