package com.example.p14gupta.popularmoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import static com.example.p14gupta.popularmoviedb.R.layout.*;

/**
 * Created by p14gupta on 05-03-2016.
 * This Activity gets data from Internet Parser it and then pass it to next activity when clicked
 */
public class PopMovieMainPage extends AppCompatActivity {

    final String APIKey = "PLACE YOUR API KEY HERE !!!";
    final String PopularMovURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+APIKey;
    final String HighratedMovURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_count.desc&api_key="+APIKey;
    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    GetConnectedToMovieDB client = new GetConnectedToMovieDB();
    MovieInfoDb[] MovieInfoDbStr =new MovieInfoDb[100];
    JsonParser Parser = new JsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popularmovie_mainpage);

        client.execute(PopularMovURL);
        try {
                MovieInfoDbStr = Parser.GetMovieDataFromJson(client.get().toString());

                GridView gridview = (GridView) findViewById(R.id.gridView);
                gridview.setAdapter(new DisplayAdapter(PopMovieMainPage.this,MovieInfoDbStr));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    MovieInfoDb item = (MovieInfoDb) parent.getItemAtPosition(position);
                    //Create intent
                    Intent intent = new Intent(PopMovieMainPage.this, PopMoviesNextPage.class);

                    intent.putExtra("title",item.MovieTitle);
                    intent.putExtra("imageURL",item.MoviePosterURL);
                    intent.putExtra("overview",item.Overview);
                    intent.putExtra("ReleaseDate",item.ReleaseDate);
                    intent.putExtra("UserRating",item.UserRating);
                    //Start details activity
                    startActivity(intent);
                }
                });

        }catch(Exception e){
            Log.e(LOG_TAG, "Error after post execute");
            Toast.makeText(getApplicationContext(), "Internet Connection Not Available",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.MostPopular) {
            GetConnectedToMovieDB clientPop = new GetConnectedToMovieDB();
            clientPop.execute(PopularMovURL);
            try{
                MovieInfoDbStr = Parser.GetMovieDataFromJson(clientPop.get().toString());

                GridView gridview = (GridView) findViewById(R.id.gridView);
                gridview.setAdapter(new DisplayAdapter(PopMovieMainPage.this,MovieInfoDbStr));
            }catch(Exception e) {
                Log.e(LOG_TAG, "Error after post execute");
                Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return true;
        }
        else if(id == R.id.MostVoted)
        {
            GetConnectedToMovieDB clientHigh = new GetConnectedToMovieDB();
            clientHigh.execute(HighratedMovURL);
            try{
                MovieInfoDbStr = Parser.GetMovieDataFromJson(clientHigh.get().toString());

                GridView gridview = (GridView) findViewById(R.id.gridView);
                gridview.setAdapter(new DisplayAdapter(PopMovieMainPage.this,MovieInfoDbStr));
            }catch(Exception e) {
                Log.e(LOG_TAG, "Error after post execute");
                Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return true;
        }
        else if(id == R.id.Aboutus)
        {
            Toast.makeText(getApplicationContext(), "Everything You Know!! ", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
