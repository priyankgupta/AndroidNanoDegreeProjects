package com.example.p14gupta.popularmoviedb.FavMovieHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.SqlLiteDB.MovieContract;

/**
 * Created by p14gupta on 21-05-2016.
 * To Add or remove Movie from Db when Favourite Button is pressed
 */
public class AddOrRemoveMovieFromFavDb extends AsyncTask<MovieInfoDb, String, Boolean > {

    private Boolean IsPresentInDb;
    private MovieInfoDb Movie;
    private Context mContext;
    private Integer FoundMovie;

    public AddOrRemoveMovieFromFavDb(Context context){
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

            int delete = mContext.getContentResolver().delete(
                    MovieContract.FavoriteMovieEntry.CONTENT_URI,
                    MovieContract.FavoriteMovieEntry.ID + " = ?",
                    new String[]{String.valueOf(Movie.MovieId)});

            IsPresentInDb = false;
        } else {

            ContentValues values = new ContentValues();

            values.put(MovieContract.FavoriteMovieEntry.ID, Movie.MovieId);
            values.put(MovieContract.FavoriteMovieEntry.ORIGINAL_TITLE, Movie.MovieTitle);
            values.put(MovieContract.FavoriteMovieEntry.POSTER, Movie.MoviePosterURL);
            values.put(MovieContract.FavoriteMovieEntry.BACKDROP, Movie.backdropPath);
            values.put(MovieContract.FavoriteMovieEntry.OVERVIEW, Movie.Overview);
            values.put(MovieContract.FavoriteMovieEntry.VOTE_AVERAGE, Movie.UserRating);
            values.put(MovieContract.FavoriteMovieEntry.RELEASE_DATE, Movie.ReleaseDate);

            mContext.getContentResolver().insert(MovieContract.FavoriteMovieEntry.CONTENT_URI, values);

            IsPresentInDb = true;
        }

        return IsPresentInDb;
    }

    @Override
    protected void onPostExecute(Boolean IsPresentInDb) {
        super.onPostExecute(IsPresentInDb);
    }
}
