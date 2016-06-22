package com.example.p14gupta.popularmoviedb.Activity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.p14gupta.popularmoviedb.Fragments.NextPageDetailFragment;
import com.example.p14gupta.popularmoviedb.POJO.MovieTrailerLinkResults;
import com.example.p14gupta.popularmoviedb.R;
import com.example.p14gupta.popularmoviedb.RestInterface.GetConnectedToMovieTrailerLink;


import java.util.List;

import static com.example.p14gupta.popularmoviedb.R.layout.popularomovie_nextpage;

/**
 * Created by p14gupta on 05-03-2016.
 * Next Page Activity for Display Movie Details
 */
public class PopMoviesNextPage extends AppCompatActivity {

    private final String LOG_TAG = PopMoviesNextPage.class.getSimpleName();
    List<MovieTrailerLinkResults> SelectedMovieTrailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popularomovie_nextpage);


        if (savedInstanceState == null) {
            NextPageDetailFragment fragment = new NextPageDetailFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.movie_details,
                    fragment).commit();
        }


        /*To share Youtube Video */
        MovieInfoDb MovieSelected_Db = getIntent().getExtras().getParcelable("Movie_To_Next");
        GetConnectedToMovieTrailerLink clientTrailer = new GetConnectedToMovieTrailerLink();
        clientTrailer.execute(MovieSelected_Db.MovieId);
        try{
            SelectedMovieTrailers = clientTrailer.get();
        }catch(Exception e) {
            Log.e(LOG_TAG, "Error Share Youtube Video");
            Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.next_page_share, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"#PopularMovies " + "https://www.youtube.com/watch?v="+ SelectedMovieTrailers.get(0).getKey());
        mShareActionProvider.setShareIntent(shareIntent);
        return true;
    }
}
