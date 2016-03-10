package com.example.p14gupta.popularmoviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.p14gupta.popularmoviedb.R.layout.popularomovie_nextpage;

/**
 * Created by p14gupta on 05-03-2016.
 * Next Page Activity for Display Movie Details
 */
public class PopMoviesNextPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popularomovie_nextpage);

        String MovieSelected = getIntent().getStringExtra("title");
        String Overview = getIntent().getStringExtra("overview");
        String UserRating = getIntent().getStringExtra("UserRating");
        String ReleaseDate = getIntent().getStringExtra("ReleaseDate");
        String ImageDisplayURL = getIntent().getStringExtra("imageURL");

        TextView MovieTitleDisplay = (TextView)findViewById(R.id.tMovieTitle);
        TextView OverviewDisplay = (TextView)findViewById(R.id.tOverview);
        TextView UserRatingDisplay = (TextView)findViewById(R.id.tUserRating);
        TextView ReleaseDateDisplay= (TextView)findViewById(R.id.tReleaseDate);
        ImageView MovieImage= (ImageView)findViewById(R.id.MovieimageNextPage);

        MovieTitleDisplay.setText(MovieSelected);
        OverviewDisplay.setText(Overview);
        UserRatingDisplay.setText(UserRating);
        ReleaseDateDisplay.setText(ReleaseDate);
        Picasso.with(this)
                .load(ImageDisplayURL)
                .into(MovieImage);

    }
}
