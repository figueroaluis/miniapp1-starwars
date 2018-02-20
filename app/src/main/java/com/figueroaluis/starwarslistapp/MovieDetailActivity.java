package com.figueroaluis.starwarslistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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

    // submit button
    private Button submitButton;

    // set boolean variables for pressing the buttons
    private boolean hasSeen;
    private boolean wantToSee;
    private boolean didNotLike;

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
        submitButton = detailScrollView.findViewById(R.id.submit_button);

        final int position = this.getIntent().getExtras().getInt("position");

        // set an onClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent submitButtonIntent = new Intent();

                // get the position

                // put the boolean values into the intent and send back
                submitButtonIntent.putExtra("hasSeen", hasSeen);
                submitButtonIntent.putExtra("wantToSee", wantToSee);
                submitButtonIntent.putExtra("didNotLike", didNotLike);
                submitButtonIntent.putExtra("position", position);


                setResult(RESULT_OK, submitButtonIntent);

                finish(); // this will finish the activity
            }

        });



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

    public void hasSeen(View view){
        hasSeen = ((RadioButton) view).isChecked();
    }

    public void wantToSee(View view){
        wantToSee = ((RadioButton) view).isChecked();
    }

    public void didNotLike(View view){
        didNotLike = ((RadioButton) view).isChecked();
    }

}
