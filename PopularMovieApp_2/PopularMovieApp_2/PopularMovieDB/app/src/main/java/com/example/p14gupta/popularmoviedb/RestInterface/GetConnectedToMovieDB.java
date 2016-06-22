package com.example.p14gupta.popularmoviedb.RestInterface;

import android.os.AsyncTask;
import android.util.Log;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.POJO.PageResults;
import com.example.p14gupta.popularmoviedb.Activity.PopMovieMainPage;

import java.io.IOException;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class GetConnectedToMovieDB extends AsyncTask<String, String, MovieInfoDb[] > {

    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    private final String API_KEY = "PUT_YOUR_API_KEY_HERE";
    private final String BASE_URL = "http://api.themoviedb.org/";
    private final String ABSOLUTE_PATH_IMAGE = "http://image.tmdb.org/t/p/w185/";

    int currentPage =1;

    MovieInfoDb[] MovieInfoDbStr;

    @Override
    protected MovieInfoDb[] doInBackground(String... params) {

        Log.i(LOG_TAG, "Retrofit Report Response");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestInterface service = retrofit.create(RestInterface.class);

        try {

            Call<PageResults> call = service.getMovieDbFromInternet(params[0],API_KEY,currentPage);
            Response<PageResults> response = call.execute();

                    if (response.isSuccess()) {
                        Log.i(LOG_TAG, "Retrofit Response Success");

                        MovieInfoDbStr = new MovieInfoDb[response.body().getResults().size()];

                        for (int i = 0; i < MovieInfoDbStr.length; i++) {

                            MovieInfoDbStr[i] = new MovieInfoDb();
                            MovieInfoDbStr[i].MovieTitle = response.body().getResults().get(i).getTitle();
                            MovieInfoDbStr[i].MoviePosterURL = GetAbsoluteImagePath(response.body().getResults().get(i).getPosterPath());
                            MovieInfoDbStr[i].Overview = response.body().getResults().get(i).getOverview();
                            MovieInfoDbStr[i].UserRating = response.body().getResults().get(i).getVoteAverage();
                            MovieInfoDbStr[i].ReleaseDate = response.body().getResults().get(i).getReleaseDate();
                            MovieInfoDbStr[i].MovieId = response.body().getResults().get(i).getId();
                            MovieInfoDbStr[i].backdropPath = GetAbsoluteImagePath(response.body().getResults().get(i).getBackdropPath());

                            Log.i("Retrofit parser", MovieInfoDbStr[i].MovieTitle);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].MoviePosterURL);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].Overview);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].UserRating);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].ReleaseDate);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].MovieId);
                            Log.i("Retrofit parser", MovieInfoDbStr[i].backdropPath);

                        }
                    }
            }
            catch (IOException e1) {
                Log.e(LOG_TAG, "Retrofit Execute Failed in Movie Fetch !!");
                e1.printStackTrace();
            }

        return MovieInfoDbStr;
    }

    @Override
    protected void onPostExecute(MovieInfoDb[] movieInfoDbs) {
        super.onPostExecute(movieInfoDbs);
    }

    protected String GetAbsoluteImagePath(String path) {
        /*To make Poster path Absolute path */
        StringBuilder AbsolutePath = new StringBuilder(path);
        AbsolutePath.deleteCharAt(0);
        AbsolutePath.insert(0, ABSOLUTE_PATH_IMAGE);
        return AbsolutePath.toString();
    }
}

