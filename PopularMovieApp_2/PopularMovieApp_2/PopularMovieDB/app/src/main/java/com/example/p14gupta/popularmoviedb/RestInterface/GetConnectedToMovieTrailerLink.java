package com.example.p14gupta.popularmoviedb.RestInterface;

import android.os.AsyncTask;
import android.util.Log;

import com.example.p14gupta.popularmoviedb.POJO.MovieTrailerLinkResults;
import com.example.p14gupta.popularmoviedb.POJO.MovieTrailersLink;
import com.example.p14gupta.popularmoviedb.Activity.PopMovieMainPage;
import com.example.p14gupta.popularmoviedb.R;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by p14gupta on 04-05-2016.
 *
 */
public class GetConnectedToMovieTrailerLink extends AsyncTask<String, String, List <MovieTrailerLinkResults> > {

    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    private final String API_KEY = "PUT_YOUR_API_KEY_HERE";
    private final String BASE_URL = "http://api.themoviedb.org/";

    List<MovieTrailerLinkResults> movierTailerInfo;

    @Override
    protected List <MovieTrailerLinkResults> doInBackground(String... params) {

        Log.i(LOG_TAG, "Retrofit Report Response");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestInterface service = retrofit.create(RestInterface.class);

        try {

            final Call<MovieTrailersLink> call = service.getMovieTrailerFromInternet(params[0], API_KEY);

            Response<MovieTrailersLink> response = call.execute();

            if (response.isSuccess()) {

                Log.i(LOG_TAG, "Retrofit Response Success Trailer");
                movierTailerInfo = response.body().getResults();
                }
            }
        catch (IOException e1) {
            Log.e(LOG_TAG, "Retrofit Execute Failed !!-Movie Trailer Link");
            e1.printStackTrace();
        }

        return movierTailerInfo;
    }

    @Override
    protected void onPostExecute(List <MovieTrailerLinkResults> movierTailerInfo) {
        super.onPostExecute(movierTailerInfo);
    }

}


