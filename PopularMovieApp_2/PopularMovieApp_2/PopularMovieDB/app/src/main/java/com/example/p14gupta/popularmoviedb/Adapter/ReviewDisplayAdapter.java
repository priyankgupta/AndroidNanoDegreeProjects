package com.example.p14gupta.popularmoviedb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p14gupta.popularmoviedb.POJO.MovieReviewResults;
import com.example.p14gupta.popularmoviedb.R;

import java.util.List;

/**
 * Created by p14gupta on 09-05-2016.
 * Review Adapter for display Movie Reviews
 */
public class ReviewDisplayAdapter extends ArrayAdapter {

    private final String LOG_TAG = DisplayAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;
    private List<MovieReviewResults> Reviews;

    public ReviewDisplayAdapter(Context context, List<MovieReviewResults> ReviewList) {

        super(context, R.layout.list_view_review_layout, ReviewList);

        this.context = context;
        this.Reviews = ReviewList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder Holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_view_review_layout, parent, false);
            Holder = new ViewHolder();
            Holder.Author = (TextView) convertView.findViewById(R.id.author);
            Holder.Comments = (TextView) convertView.findViewById(R.id.comments);
            convertView.setTag(Holder);
        }
        else {
            Holder = (ViewHolder) convertView.getTag();
        }

        Holder.Author.setText(GetAuthorName(position));
        Holder.Comments.setText(GetAuthorComments(position));

        return convertView;
    }

    public String GetAuthorName(int position){
        Log.v(LOG_TAG, Reviews.get(position).getAuthor());
        return Reviews.get(position).getAuthor();
    }

    public String GetAuthorComments(int position){
        Log.v(LOG_TAG, Reviews.get(position).getContent().substring(0,20));
        return Reviews.get(position).getContent();
    }

    static class ViewHolder{
        TextView Author;
        TextView Comments;
    }


}
