package com.example.p14gupta.popularmoviedb.FavMovieHandler;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.SqlLiteDB.MovieContract;

/**
 * Created by p14gupta on 21-05-2016.
 * Check If current movie is present in Db,Used for Changing the Favourite button to be true or false
 */
public class CheckIfMovieInFavDb extends AsyncTask<MovieInfoDb, String, Boolean > {

    private Boolean IsPresentInDb;
    private MovieInfoDb Movie;
    private Context mContext;
    private Integer FoundMovie;

    public CheckIfMovieInFavDb(Context context){
        this.mContext=context;
    }
    @Override
    protected Boolean doInBackground(MovieInfoDb... params) {

        Movie = params[0];

        /*check if movie in db*/
        Cursor cursor = mContext.getContentResolver().query(
                MovieContract.FavoriteMovieEntry.CONTENT_URI,
                null,   //projection
                MovieContract.FavoriteMovieEntry.ID + " =?",
                new String[]{String.valueOf(Movie.MovieId)},      // selectionArgs : gets the rows with this movieID
                null             // Sort order
        );

        if (cursor != null) {
            FoundMovie = cursor.getCount();
            cursor.close();
        }

        if (FoundMovie == 1) {
            IsPresentInDb = true;
        } else {
            IsPresentInDb = false;
        }

        return IsPresentInDb;
    }

    @Override
    protected void onPostExecute(Boolean IsPresentInDb) {
        super.onPostExecute(IsPresentInDb);
    }

}
