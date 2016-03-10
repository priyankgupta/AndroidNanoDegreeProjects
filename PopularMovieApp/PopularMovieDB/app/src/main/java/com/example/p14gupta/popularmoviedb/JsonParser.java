package com.example.p14gupta.popularmoviedb;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by p14gupta on 06-03-2016.
 * To Parser the Data from Internet
 */
public class JsonParser {

    public MovieInfoDb[] GetMovieDataFromJson(String MovieDbJsonStr)
            throws JSONException {

        final String NUM_MOVIE ="results";
        final String MOVIE_TITLE = "original_title";
        final String MOVIE_POSTER_URL = "poster_path";
        final String OVERVIEW = "overview";
        final String USER_RATING = "vote_average";
        final String RELEASE_DATE = "release_date";
        final String ABSOLUTE_PATH = "http://image.tmdb.org/t/p/w185/";

        JSONObject MovieDbJson = new JSONObject(MovieDbJsonStr);
        JSONArray MovieDbJsonArray = MovieDbJson.getJSONArray(NUM_MOVIE);

        MovieInfoDb[] MovieInfoDbStr =  new MovieInfoDb[MovieDbJsonArray.length()];

        for(int i= 0;i< MovieDbJsonArray.length();i++ )
        {
            JSONObject MovieDb = MovieDbJsonArray.getJSONObject(i);

            MovieInfoDbStr[i] = new MovieInfoDb();

            MovieInfoDbStr[i].MovieTitle = MovieDb.getString(MOVIE_TITLE);
            MovieInfoDbStr[i].MoviePosterURL = MovieDb.getString(MOVIE_POSTER_URL);
            MovieInfoDbStr[i].Overview = MovieDb.getString(OVERVIEW);
            MovieInfoDbStr[i].UserRating = MovieDb.getString(USER_RATING);
            MovieInfoDbStr[i].ReleaseDate = MovieDb.getString(RELEASE_DATE);

            /*To make Poster path Absolute path */
            StringBuilder AbsolutePath = new StringBuilder(MovieInfoDbStr[i].MoviePosterURL);
            AbsolutePath.deleteCharAt(0);
            AbsolutePath.insert(0,ABSOLUTE_PATH);
            MovieInfoDbStr[i].MoviePosterURL = AbsolutePath.toString();

            Log.i("JSOn parser",MovieInfoDbStr[i].MovieTitle);
            Log.i("JSOn parser",MovieInfoDbStr[i].MoviePosterURL);
            Log.i("JSOn parser",MovieInfoDbStr[i].Overview);
            Log.i("JSOn parser",MovieInfoDbStr[i].UserRating);
            Log.i("JSOn parser",MovieInfoDbStr[i].ReleaseDate);

        }

        return MovieInfoDbStr;
    }
}
