package com.figueroaluis.starwarslistapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by luisfigueroa on 2/10/18.
 */

public class MovieDetailActivity extends AppCompatActivity {

    // create the field for the scroll view
    private Context mContext;
    private TextView title;
    private ImageView poster;
    private TextView description;

    private ScrollView detailScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);


        detailScrollView = findViewById(R.id.scroll_view_movie_detail);

        // connect the variables to the views and app
        mContext = this;

        title = detailScrollView.findViewById(R.id.movie_detail_title);
        poster = detailScrollView.findViewById(R.id.movie_detail_poster);
        description = detailScrollView.findViewById(R.id.movie_detail_description);

        // need to put the title of the movie as title of the activity
        // need the url for the poster
        // get the description and put it on the activity
        String descriptionText = this.getIntent().getExtras().getString("description");
        String titleText = this.getIntent().getExtras().getString("title");
        String posterUrl = this.getIntent().getExtras().getString("poster");
        // set the movie title as title for the page

        setTitle(titleText);

        Picasso.with(mContext).load(posterUrl).into(poster);

        // title view
        title.setText(titleText);
        title.setTextSize(34);

        // description view
        description.setText(descriptionText);
        description.setTextSize(14);

        // create the scroll view and load all the info

    }
}
