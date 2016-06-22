package com.example.p14gupta.popularmoviedb.Fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.Activity.PopMoviesNextPage;
import com.example.p14gupta.popularmoviedb.Activity.SeeUserReviews;
import com.example.p14gupta.popularmoviedb.FavMovieHandler.AddOrRemoveMovieFromFavDb;
import com.example.p14gupta.popularmoviedb.FavMovieHandler.CheckIfMovieInFavDb;
import com.example.p14gupta.popularmoviedb.POJO.MovieTrailerLinkResults;
import com.example.p14gupta.popularmoviedb.R;
import com.example.p14gupta.popularmoviedb.RestInterface.GetConnectedToMovieTrailerLink;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by p14gupta on 22-05-2016.
 * Fragment for detail display of Movie
 */
public class NextPageDetailFragment extends Fragment {

    /*Use Butter Knife*/
    @Bind(R.id.tMovieTitle)
    TextView MovieTitleDisplay;
    @Bind(R.id.tOverview) TextView OverviewDisplay;
    @Bind(R.id.tUserRating) TextView UserRatingDisplay;
    @Bind(R.id.tReleaseDate) TextView ReleaseDateDisplay;
    @Bind(R.id.MovieimageNextPage) ImageView MovieImage;
    @Bind(R.id.reviewbutton) Button bReviewButton;
    @Bind(R.id.YoutubeTrailer) Button bTrailerButton;
    @Bind(R.id.FavouriteMovieButton) CheckBox cFavouriteMovieCheckbox;

    List<MovieTrailerLinkResults> SelectedMovieTrailers;
    Boolean FoundMovieInDb;
    Boolean MovieOperation;
    MovieInfoDb MovieSelected_Db;

    private final String LOG_TAG = PopMoviesNextPage.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_layout, container, false);

        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            MovieSelected_Db = bundle.getParcelable("Movie_To_Next");
        }

        assert MovieSelected_Db != null;

        String MovieSelected = MovieSelected_Db.MovieTitle;
        String Overview = MovieSelected_Db.Overview;
        String UserRating = MovieSelected_Db.UserRating;
        String ReleaseDate = MovieSelected_Db.ReleaseDate;
        String ImageDisplayURL = MovieSelected_Db.MoviePosterURL;
        final String MovieId = MovieSelected_Db.MovieId;

        MovieTitleDisplay.setText(MovieSelected);
        OverviewDisplay.setText(Overview);
        UserRatingDisplay.setText(UserRating);
        ReleaseDateDisplay.setText(ReleaseDate);
        Picasso.with(getActivity())
                .load(ImageDisplayURL)
                .into(MovieImage);


        bReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SeeUserReviews.class);
                intent.putExtra("Movie_Id", MovieId);
                //Start details activity
                startActivity(intent);
            }
        });

        bTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetConnectedToMovieTrailerLink clientTrailer = new GetConnectedToMovieTrailerLink();
                clientTrailer.execute(MovieId);
                try{

                    SelectedMovieTrailers = clientTrailer.get();
                }catch(Exception e) {
                    Log.e(LOG_TAG, "Error after post execute");
                    Toast.makeText(getActivity().getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                if(SelectedMovieTrailers.size()>0)
                {
                    // Build the intent
                    Uri link = Uri.parse("https://www.youtube.com/watch?v="+SelectedMovieTrailers.get(0).getKey());
                    Intent videoIntent = new Intent(Intent.ACTION_VIEW, link);

                    // Verify it resolves
                    PackageManager packageManager = getActivity().getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(videoIntent, 0);
                    boolean isIntentSafe = activities.size() > 0;

                    // Start an activity if it's safe
                    if (isIntentSafe) {
                        startActivity(videoIntent);
                    }
                    else
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "No Apps available to Play Trailer", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "No Trailer for this Movie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        try{
            /*check if movie already in Db or not, Set Favourite button */
            CheckIfMovieInFavDb isMovieInDb = new CheckIfMovieInFavDb(getActivity().getApplicationContext());
            isMovieInDb.execute(MovieSelected_Db);

            FoundMovieInDb = isMovieInDb.get();

            if(FoundMovieInDb){
                cFavouriteMovieCheckbox.setChecked(true);
            }
            else{
                cFavouriteMovieCheckbox.setChecked(false);
            }
        }
        catch (Exception e) {
            //No Exception handling
        }

        cFavouriteMovieCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    AddOrRemoveMovieFromFavDb HandleMovie = new AddOrRemoveMovieFromFavDb(getActivity().getApplicationContext());
                    HandleMovie.execute(MovieSelected_Db);
                    MovieOperation = HandleMovie.get();

                }catch (Exception e){
                    /*Nothing*/
                }

                if(MovieOperation){

                    cFavouriteMovieCheckbox.setChecked(true);
                    Toast.makeText(getActivity().getApplicationContext(), "Movie Added to your Favorite list", Toast.LENGTH_SHORT).show();
                }
                else{
                    cFavouriteMovieCheckbox.setChecked(false);
                    Toast.makeText(getActivity().getApplicationContext(), "Movie Removed from your Favorite list", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }


}


