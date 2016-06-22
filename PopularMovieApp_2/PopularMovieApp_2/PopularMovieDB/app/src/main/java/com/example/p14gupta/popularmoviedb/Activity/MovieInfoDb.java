package com.example.p14gupta.popularmoviedb.Activity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by p14gupta on 06-03-2016.
 * Movie Info Db
 */
public class MovieInfoDb implements Parcelable {

    @SerializedName("original_title")
    public String MovieTitle;
    @SerializedName("poster_path")
    public String MoviePosterURL;
    @SerializedName("overview")
    public String Overview;
    @SerializedName("vote_average")
    public String UserRating;
    @SerializedName("release_date")
    public String ReleaseDate;
    @SerializedName("id")
    public String MovieId;
    @SerializedName("backdrop_path")
    public String backdropPath;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MovieTitle);
        dest.writeString(MoviePosterURL);
        dest.writeString(Overview);
        dest.writeString(UserRating);
        dest.writeString(ReleaseDate);
        dest.writeString(MovieId);
        dest.writeString(backdropPath);
    }

    //Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MovieInfoDb createFromParcel(Parcel in) {
            return new MovieInfoDb(in);
        }

        @Override
        public MovieInfoDb[] newArray(int size) {
            return new MovieInfoDb[size];
        }
    };

   //Cursor
    static final int COL_MOVIE_ID =0;
    static final int COL_ORIGINAL_TITLE = 1;
    static final int COL_OVERVIEW = 2;
    static final int COL_POSTER = 3;
    static final int COL_RATING = 4;
    static final int COL_DATE = 5;
    static final int COL_BACKDROP = 6;


    public MovieInfoDb(Cursor cursor) {

        this.MovieId = cursor.getString(COL_MOVIE_ID);
        this.MovieTitle = cursor.getString(COL_ORIGINAL_TITLE);
        this.MoviePosterURL = cursor.getString(COL_POSTER);
        this.backdropPath = cursor.getString(COL_BACKDROP);
        this.Overview = cursor.getString(COL_OVERVIEW);
        this.UserRating = cursor.getString(COL_RATING);
        this.ReleaseDate = cursor.getString(COL_DATE);
    }

    //De-Parcel
    public MovieInfoDb(Parcel in) {
        MovieTitle = in.readString();
        MoviePosterURL = in.readString();
        Overview = in.readString();
        UserRating = in.readString();
        ReleaseDate = in.readString();
        MovieId = in.readString();
        backdropPath = in.readString();
    }

    public MovieInfoDb()
    {
        //Initilaize Constructor
    }
}