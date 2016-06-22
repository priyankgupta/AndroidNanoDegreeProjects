package com.example.p14gupta.popularmoviedb.RestInterface;

import android.os.AsyncTask;
import android.util.Log;

import com.example.p14gupta.popularmoviedb.POJO.MovieReview;
import com.example.p14gupta.popularmoviedb.POJO.MovieReviewResults;
import com.example.p14gupta.popularmoviedb.Activity.PopMovieMainPage;

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
public class GetConnectedToMovieReview extends AsyncTask<String, String, List<MovieReviewResults> > {

    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    private final String API_KEY = "PUT_YOUR_API_KEY_HERE";
    private final String BASE_URL = "http://api.themoviedb.org/";
   

    MovieReview movierReviewInfo;

    @Override
    protected  List<MovieReviewResults> doInBackground(String... params) {

        Log.i(LOG_TAG, "Retrofit Report Response");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestInterface service = retrofit.create(RestInterface.class);

        try {

            final Call<MovieReview> call = service.getMovieReviewFromInternet(params[0], API_KEY);

            Response<MovieReview> response = call.execute();

            if (response.isSuccess()) {

                Log.i(LOG_TAG, "Retrofit Response Success Review");
                Log.i(LOG_TAG, response.body().getResults().size()+ "!");

                movierReviewInfo = response.body();
            }
        }
        catch (IOException e1) {
            Log.e(LOG_TAG, "Retrofit Execute Failed !!-Movie Review");
            e1.printStackTrace();
        }

        return movierReviewInfo.getResults();
    }

    @Override
    protected void onPostExecute(List<MovieReviewResults> movierTailerInfo) {
        super.onPostExecute(movierTailerInfo);
    }

}




