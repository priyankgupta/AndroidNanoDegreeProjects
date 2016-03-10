package com.example.p14gupta.popularmoviedb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by p14gupta on 06-03-2016.
 * Display Adapter for loading Images in Grid View
 */
public class DisplayAdapter extends ArrayAdapter {
        private Context context;
        private LayoutInflater inflater;
        private final String LOG_TAG = DisplayAdapter.class.getSimpleName();

        private String[] imageUrls;

        public DisplayAdapter(Context context, MovieInfoDb[] MovieDbStr) {
            super(context, R.layout.grid_view_layout, MovieDbStr);
            
            this.context = context;
            this.imageUrls = GetMovieURL(MovieDbStr);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.grid_view_layout, parent, false);
            }
            Picasso.with(context)
                    .load(imageUrls[position])
                    .fit()
                    .into((ImageView) convertView);
            return convertView;
        }

        public String[] GetMovieURL(MovieInfoDb[] MovieInfoDbStr){

            String[] returnStr = new String[MovieInfoDbStr.length];

            for(int i=0; i < MovieInfoDbStr.length;i++)
            {
                returnStr[i] = MovieInfoDbStr[i].MoviePosterURL;
                Log.v(LOG_TAG, returnStr[i]);
            }
            return returnStr;
    }
    }
