package com.example.p14gupta.popularmoviedb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p14gupta.popularmoviedb.Activity.MovieInfoDb;
import com.example.p14gupta.popularmoviedb.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by p14gupta on 06-03-2016.
 * Display Adapter for loading Images in Grid View
 */
public class DisplayAdapter extends ArrayAdapter {

        private Context context;
        private LayoutInflater inflater;
        private final String LOG_TAG = DisplayAdapter.class.getSimpleName();

        private String[] imageUrls;
        private String[] moviename;


        public DisplayAdapter(Context context, MovieInfoDb[] MovieDbStr) {
            super(context, R.layout.grid_view_layout, MovieDbStr);
            
            this.context = context;
            this.imageUrls = GetMovieURL(MovieDbStr);
            this.moviename = GetMovieName(MovieDbStr);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder Holder = new ViewHolder();
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.grid_view_layout, parent, false);

                Holder.Poster = (ImageView) convertView.findViewById(R.id.MoviePoster);
                Holder.Title = (TextView) convertView.findViewById(R.id.MoviePosterTitle);
                convertView.setTag(Holder);
            }
            else {
                Holder = (ViewHolder) convertView.getTag();
            }

            Holder.Title.setText(moviename[position]);

            Picasso.with(context)
                    .load(imageUrls[position])
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .fit()
                    .into(Holder.Poster);


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

    public String[] GetMovieName(MovieInfoDb[] MovieInfoDbStr){

        String[] returnStr = new String[MovieInfoDbStr.length];

        for(int i=0; i < MovieInfoDbStr.length;i++)
        {
            returnStr[i] = MovieInfoDbStr[i].MovieTitle;
            Log.v(LOG_TAG, returnStr[i]);
        }
        return returnStr;
    }

    static class ViewHolder{
        TextView Title;
        ImageView Poster;
    }
}
