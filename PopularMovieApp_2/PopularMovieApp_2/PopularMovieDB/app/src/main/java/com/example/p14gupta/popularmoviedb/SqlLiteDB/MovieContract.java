package com.example.p14gupta.popularmoviedb.SqlLiteDB;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by p14gupta on 15-05-2016.
 *
 */
public class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.p14gupta.poplarMovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAVORITE = "favoriteMovie";

    public static final class FavoriteMovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE;
        public static final String TABLE_NAME = "favorite";
        public static final String ID = "Movie_Id";
        public static final String ORIGINAL_TITLE = "originalTitle";
        public static final String OVERVIEW = "overview";
        public static final String POSTER = "poster";
        public static final String VOTE_AVERAGE = "voteAverage";
        public static final String RELEASE_DATE = "releaseDate";
        public static final String BACKDROP = "backdropUrl";

        public static Uri buildFavoriteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
