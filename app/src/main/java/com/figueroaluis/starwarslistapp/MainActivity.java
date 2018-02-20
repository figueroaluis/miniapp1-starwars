package com.figueroaluis.starwarslistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // create a listview variable
    private ListView mListView;
    // create a context variable
    private Context mContext;

    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;
    private TextView movieStatusView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set mContext to this
        mContext = this;

        // data to display
        movieList = Movie.getMoviesFromFile("movies.json", this);


        // create the adapter
        adapter = new MovieAdapter(this, movieList);

        // find the list view in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);


        // each row should be clickable
        // so that a movie detail page is created when you click on it
        // create an onClickListener and do some override
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);

                // create the intent package
                // so that we get the info from the file on to the detail page
                // start detail Activity with that intent

                // this is an explicit intent
                // from, to
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                // put the title, the poster url, and the description
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("poster", selectedMovie.posterUrl);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("position", position);

                // start the activity by passing the intent
                startActivityForResult(detailIntent, 1);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // include every time we want to override stuff!
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // this means that the second activity is sending data
                boolean hasSeen = data.getBooleanExtra("hasSeen", false);
                boolean wantToSee = data.getBooleanExtra("wantToSee", false);
                boolean didNotLike = data.getBooleanExtra("didNotLike", false);
                int position = data.getIntExtra("position", 0);


                // this is for debugging
                // int position = data.getIntExtra("position", 0);

                if (hasSeen) {
                    movieList.get(position).movieStatus = "Have Seen It";
                } else if (wantToSee) {
                    movieList.get(position).movieStatus = "Want To Watch It";
                } else if (didNotLike) {
                    movieList.get(position).movieStatus = "Didn't Like It";
                }

                adapter.notifyDataSetChanged();
            }

        }
    }

}
