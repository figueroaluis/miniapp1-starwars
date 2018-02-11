package com.figueroaluis.starwarslistapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // data to display
        ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        // find the list view in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

    }
}
