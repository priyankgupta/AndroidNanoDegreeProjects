package com.example.p14gupta.popularmoviedb.RestInterface;

import com.example.p14gupta.popularmoviedb.POJO.MovieReview;
import com.example.p14gupta.popularmoviedb.POJO.MovieTrailersLink;
import com.example.p14gupta.popularmoviedb.POJO.PageResults;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by p14gupta on 30-04-2016.
 * Rest interface for RetroFit
 */
public interface RestInterface {


    @GET("3/movie/{sortBy}")
    Call<PageResults> getMovieDbFromInternet(@Path("sortBy") String sort,@Query("api_key")String api_key,@Query("page") int page);

    @GET("3/movie/{id}/reviews")
    Call<MovieReview> getMovieReviewFromInternet(@Path("id") String movieId,@Query("api_key")String api_key);

    @GET("3/movie/{id}/videos")
    Call<MovieTrailersLink> getMovieTrailerFromInternet(@Path("id") String movieId,@Query("api_key")String api_key);
}
