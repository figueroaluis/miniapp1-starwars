package com.figueroaluis.starwarslistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // create a listview variable
    private ListView mListView;
    // create a context variable
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set mContext to this
        mContext = this;

        // data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

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

                // start the activity by passing the intent
                startActivity(detailIntent);
            }
        });
    }


}
