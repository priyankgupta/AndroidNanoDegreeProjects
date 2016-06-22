package com.example.p14gupta.popularmoviedb.SqlLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by p14gupta on 15-05-2016.
 *
 */
public class MovieSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favouriteMovie.db";
    private static final int DATABASE_VERSION = 1;

    final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + MovieContract.FavoriteMovieEntry.TABLE_NAME
            + "(" + MovieContract.FavoriteMovieEntry.ID + " REAL PRIMARY KEY,"
            + MovieContract.FavoriteMovieEntry.ORIGINAL_TITLE + " TEXT,"
            + MovieContract.FavoriteMovieEntry.OVERVIEW + " TEXT,"
            + MovieContract.FavoriteMovieEntry.POSTER + " TEXT,"
            + MovieContract.FavoriteMovieEntry.VOTE_AVERAGE + " TEXT,"
            + MovieContract.FavoriteMovieEntry.RELEASE_DATE + " TEXT,"
            + MovieContract.FavoriteMovieEntry.BACKDROP + " TEXT"
            + ");";

    public MovieSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
