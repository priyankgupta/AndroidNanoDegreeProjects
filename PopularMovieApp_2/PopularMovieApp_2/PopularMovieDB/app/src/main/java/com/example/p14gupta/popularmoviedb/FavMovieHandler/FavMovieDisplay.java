package com.example.p14gupta.popularmoviedb.FavMovieHandler;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.Log;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.SqlLiteDB.MovieContract;

import java.util.ArrayList;

/**
 * Created by p14gupta on 21-05-2016.
 * Grid view Display for Favourite Movies in Db
 */
public class FavMovieDisplay extends AsyncTask<Void ,Void, MovieInfoDb[]> {

    MovieInfoDb[] MovieInfoDbStr = null;
    private Context mContext;
    private  int index=0;

    public FavMovieDisplay(Context context){
        this.mContext=context;
    }

    @Override
    protected MovieInfoDb[] doInBackground(Void... params) {

        Cursor cursor = mContext.getContentResolver().query(MovieContract.FavoriteMovieEntry.CONTENT_URI, Movie_COLUMNS, null,null,null);


        if(cursor != null) {

            MovieInfoDbStr = new MovieInfoDb[cursor.getCount()];

            while (cursor.moveToNext()) {

                MovieInfoDb movie = new MovieInfoDb(cursor);

                MovieInfoDbStr[index] = new MovieInfoDb();
                MovieInfoDbStr[index]= movie;

                Log.i("Retrieve From Db", MovieInfoDbStr[index].MovieTitle);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].MoviePosterURL);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].Overview);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].UserRating);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].ReleaseDate);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].MovieId);
                Log.i("Retrieve From Db", MovieInfoDbStr[index].backdropPath);

                index++;
            }
        }
        cursor.close();

        return MovieInfoDbStr;
    }


    private static final String[] Movie_COLUMNS = {

            //Array of all the column names in Movie table
            MovieContract.FavoriteMovieEntry.TABLE_NAME + "." + MovieContract.FavoriteMovieEntry.ID,
            MovieContract.FavoriteMovieEntry.ORIGINAL_TITLE,
            MovieContract.FavoriteMovieEntry.OVERVIEW,
            MovieContract.FavoriteMovieEntry.POSTER,
            MovieContract.FavoriteMovieEntry.VOTE_AVERAGE,
            MovieContract.FavoriteMovieEntry.RELEASE_DATE,
            MovieContract.FavoriteMovieEntry.BACKDROP,

    };

    @Override
    protected void onPostExecute(MovieInfoDb[] MovieInfoDbStr) {
        super.onPostExecute(MovieInfoDbStr);
    }

}
