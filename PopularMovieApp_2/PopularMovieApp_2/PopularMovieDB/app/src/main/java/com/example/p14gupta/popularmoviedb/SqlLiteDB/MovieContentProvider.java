package com.example.p14gupta.popularmoviedb.SqlLiteDB;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by p14gupta on 15-05-2016.
 *
 */
public class MovieContentProvider extends ContentProvider {

    private static final UriMatcher UriMatcher = buildUriMatcher();
    private MovieSQLiteHelper DBOpenHelper ;
    static final int FAV_MOVIE_DB_SIZE = 50;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,MovieContract.PATH_FAVORITE,FAV_MOVIE_DB_SIZE);
        return  matcher;

    }

    @Override
    public boolean onCreate() {
        DBOpenHelper = new MovieSQLiteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;

        if(FAV_MOVIE_DB_SIZE == UriMatcher.match(uri)){
            retCursor  = DBOpenHelper.getReadableDatabase().query(
                    MovieContract.FavoriteMovieEntry.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, sortOrder);
        }
        else{
            throw new UnsupportedOperationException("URI Not Found: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        final int match = UriMatcher.match(uri);

        if(FAV_MOVIE_DB_SIZE == match)
        {
            return MovieContract.FavoriteMovieEntry.CONTENT_TYPE;
        }
        else
        {
            throw new UnsupportedOperationException("URI Not Found:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase db =  DBOpenHelper.getWritableDatabase();
        Uri returnUri;

        if(UriMatcher.match(uri)== FAV_MOVIE_DB_SIZE){

            long _movieid = db.insert(MovieContract.FavoriteMovieEntry.TABLE_NAME, null, values);

            if( _movieid >0){
                    returnUri  = MovieContract.FavoriteMovieEntry.buildFavoriteUri(_movieid);
                }
                else{
                    throw new android.database.SQLException("Failed to insert row into "+uri);
                }
            }
        else{
                throw new UnsupportedOperationException("URI Not Found: "+uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int DeleteRow;
        final SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        final int match = UriMatcher.match(uri);

        //delete all rows return
        if (null == selection) selection = "1";

        if(match == FAV_MOVIE_DB_SIZE){
            DeleteRow = db.delete(MovieContract.FavoriteMovieEntry.TABLE_NAME , selection, selectionArgs);
        }
        else {
            throw new UnsupportedOperationException("URI Not Found:" + uri);
        }


        // Because a null deletes all rows
        if (DeleteRow != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return DeleteRow;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
        int rowsUpdated =0;
        final int match = UriMatcher.match(uri);

        if(FAV_MOVIE_DB_SIZE == match) {
            rowsUpdated = db.update(MovieContract.FavoriteMovieEntry.TABLE_NAME, values, selection, selectionArgs);
        }
        else{
            throw new UnsupportedOperationException("Failed to update row "+rowsUpdated);
        }

        if(rowsUpdated!=0 ){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return rowsUpdated;

    }
}
