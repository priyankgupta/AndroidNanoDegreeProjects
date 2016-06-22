package com.example.p14gupta.popularmoviedb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.p14gupta.popularmoviedb.Adapter.ReviewDisplayAdapter;
import com.example.p14gupta.popularmoviedb.POJO.MovieReviewResults;
import com.example.p14gupta.popularmoviedb.R;
import com.example.p14gupta.popularmoviedb.RestInterface.GetConnectedToMovieReview;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by p14gupta on 21-05-2016.
 * Activity to show Reviews of selected Movie
 */

public class SeeUserReviews extends AppCompatActivity {

    List<MovieReviewResults> ReviewList;
    private final String LOG_TAG = SeeUserReviews.class.getSimpleName();

    @Bind(R.id.listreviewview) ListView ListReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_user_review);
        ButterKnife.bind(this);

        Intent GetData = getIntent();
        String MovieId = GetData.getStringExtra("Movie_Id");

        GetConnectedToMovieReview clientMovieReviews = new GetConnectedToMovieReview();
        clientMovieReviews.execute(MovieId);

        try{

            ReviewList = clientMovieReviews.get();
            if(ReviewList.size() > 0) {
                ListReview.setAdapter(new ReviewDisplayAdapter(SeeUserReviews.this, ReviewList));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No Reviews found", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e) {
            Log.e(LOG_TAG, "Error after post execute");
            Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
