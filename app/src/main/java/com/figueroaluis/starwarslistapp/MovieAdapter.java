package com.figueroaluis.starwarslistapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by luisfigueroa on 2/10/18.
 */

public class MovieAdapter extends BaseAdapter {

    // adapter will take the app and the list of data to display
    // create fields
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    // constructor that takes app and data

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        // initialize the instance variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // list of methods we need to override from BaseAdapter class

    // gives you number of movies in movie list
    @Override
    public int getCount(){
        return mMovieList.size();
    }

    // returns the item at the specific location in the data source
    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    // returns the row associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }


    // method that returns a view
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check to see if the view already exists
        // if yes, don't need to infalce and just findViewById
        if(convertView == null){
            // infalte
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            // holder.charactersTextView = convertView.findViewById(R.id.movie_list_main_characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.move_list_has_seen);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);
            // add the holder to the view, for future use
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        // TextView charactersTextView = holder.charactersTextView;
        TextView hasSeenTextView = holder.hasSeenTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        // get the correspoding movie for each row
        Movie movie = (Movie) getItem(position);

        // update the view's textview and imageview to display the information
        // title textview
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        titleTextView.setTextSize(20);

        // description textview
        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        descriptionTextView.setTextSize(9);

        // main characters text view
        // charactersTextView.setText("Placeholder");

        // has seen text view
        hasSeenTextView.setText("Has Seen?");

        // load image view
        // use the picasso library to load image from the url
        Picasso.with(mContext).load(movie.posterUrl).into(thumbnailImageView);


        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a prvate static class you have to define
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        // public TextView charactersTextView;
        public TextView hasSeenTextView;
        public ImageView thumbnailImageView;

    }

}
