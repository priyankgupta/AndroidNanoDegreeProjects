package com.example.p14gupta.popularmoviedb.Activity;

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

import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.p14gupta.popularmoviedb.Adapter.DisplayAdapter;
import com.example.p14gupta.popularmoviedb.FavMovieHandler.FavMovieDisplay;
import com.example.p14gupta.popularmoviedb.Fragments.NextPageDetailFragment;
import com.example.p14gupta.popularmoviedb.R;
import com.example.p14gupta.popularmoviedb.RestInterface.GetConnectedToMovieDB;

import static com.example.p14gupta.popularmoviedb.R.layout.*;

/**
 * Created by p14gupta on 05-03-2016.
 * This Activity gets data from Internet Parser it and then pass it to next activity when clicked
 */
public class PopMovieMainPage extends AppCompatActivity {

    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    private final String HighratedMov_TAG = "top_rated";
    private final String POPULARMov_TAG = "popular";
    private final String FavouriteMovie = "FavouriteMovie";

    MovieInfoDb[] MovieInfoDbStr;
    /*Use Butter Knife*/
    @Bind(R.id.gridView) GridView gridview;

    Bundle Movie = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean tabletSize = getResources().getBoolean(R.bool.isTablet);

        if(tabletSize){

            Log.i(LOG_TAG, "Tablet Size");
            setContentView(popularmovie_mainpage_land);
        }
        else
        {
            Log.i(LOG_TAG, "Mobile Size");
            setContentView(popularmovie_mainpage);
        }

        ButterKnife.bind(this);

        GenerateGridView(POPULARMov_TAG);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                MovieInfoDb item = (MovieInfoDb) parent.getItemAtPosition(position);

                if(tabletSize){

                    NextPageDetailFragment fragment = new NextPageDetailFragment();
                    Movie.putParcelable("Movie_To_Next",item);
                    fragment.setArguments(Movie);
                    getSupportFragmentManager().beginTransaction().replace(R.id.movie_details_tablet,
                            fragment).commit();
                }
               else{

                    Log.i(LOG_TAG, "Movie passed to Next Activity");
                    //Create intent
                    Intent intent = new Intent(getApplicationContext(), PopMoviesNextPage.class);
                    intent.putExtra("Movie_To_Next", item);
                    //Start details activity
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        ButterKnife.bind(this);

        if (id == R.id.MostPopular) {
            GenerateGridView(POPULARMov_TAG);
            return true;
        }
        else if(id == R.id.MostVoted)
        {
            GenerateGridView(HighratedMov_TAG);
            return true;
        }
        else if(id == R.id.Favourite_Movies){

            GenerateGridView(FavouriteMovie);
            return true;
        }
        else if(id == R.id.Aboutus)
        {
            Toast.makeText(getApplicationContext(), "Everything You Know!!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    protected void GenerateGridView(String SortCriteria){

        try {
            if(SortCriteria != FavouriteMovie ) {

                GetConnectedToMovieDB client = new GetConnectedToMovieDB();
                client.execute(SortCriteria);
                MovieInfoDbStr = client.get();
            }
            else{

                FavMovieDisplay FavMovies = new FavMovieDisplay(getApplicationContext());
                FavMovies.execute();
                MovieInfoDbStr = FavMovies.get();

            }

            if(MovieInfoDbStr.length != 0){
                gridview.setAdapter(new DisplayAdapter(PopMovieMainPage.this,MovieInfoDbStr));
            }
            else{
                Toast.makeText(getApplicationContext(), "No Movies To Show", Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception e) {
            Log.e(LOG_TAG, "Error In Connection or Db");
            Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }


}

