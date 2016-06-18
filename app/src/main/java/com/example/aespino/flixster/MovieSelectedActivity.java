package com.example.aespino.flixster;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by aespino on 6/17/16.
 */
public class MovieSelectedActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvOverview;
    TextView tvReleaseDate;
    RatingBar rbRating;
    ImageView ivPoster;
    ImageView ivBackdrop;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected);

        int orientation = getResources().getConfiguration().orientation;

        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String backdropUrl = getIntent().getStringExtra("posterUrl");
        String posterUrl = getIntent().getStringExtra("backdrop");
        String releaseDate = getIntent().getStringExtra("releaseDate");
        float rating = getIntent().getFloatExtra("rating", 0);

        tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvOverview=(TextView) findViewById(R.id.tvOverview);
        tvOverview.setText(overview);
        tvReleaseDate=(TextView) findViewById(R.id.tvReleaseDate);
        tvReleaseDate.setText("Release Date: " + releaseDate);

        rbRating = (RatingBar) findViewById(R.id.rbRating);

        rbRating.setNumStars(5);
        rbRating.setRating(rating/2);

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop);
            ivBackdrop.setImageResource(0);

            Picasso.with(this).load(posterUrl).into(ivBackdrop);

        }
        else{
            ivPoster = (ImageView) findViewById(R.id.ivPoster);
            ivPoster.setImageResource(0);
            Picasso.with(this).load(backdropUrl).into(ivPoster);

        }


    }
}
