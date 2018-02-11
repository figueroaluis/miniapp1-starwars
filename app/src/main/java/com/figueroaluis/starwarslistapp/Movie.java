package com.figueroaluis.starwarslistapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by luisfigueroa on 2/10/18.
 */

public class Movie {

    // instance variables or fields
    String title;
    String episode;
    //ArrayList<String> mainCharacters;
    String description;
    String posterUrl;
    String movieUrl;

    // constructor
    // default

    // method
    // this is the static method that reads the json file in and loads it into Movie

    // method will return an array list of movies constructed from the given json file

    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        // initialize the arraylist in which we'll add the movies
        ArrayList<Movie> movieList = new ArrayList<>();

        // try to read from json file
        // get information by using the tags inside json file
        // construct a Movie Object for each movie in the json file
        // add the object to an arraylist and then return the arraylist

        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");
            // JSONArray characters = json.getJSONArray("main_characters");

            // for loop to populate the arraylist with movies
            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode = movies.getJSONObject(i).getString("episode_number");
                // do a for loop here to populate it with an array list
//                JSONArray main_characters = movies.getJSONObject(i).getJSONArray("main_characters");
//                for(int j = 0; j < main_characters.length(); j++){
//                    movie.mainCharacters.add(main_characters.getJSONObject(j).toString());
//                }
                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterUrl = movies.getJSONObject(i).getString("poster");
                movie.movieUrl = movies.getJSONObject(i).getString("url");

                // add to arraylist
                movieList.add(movie);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return movieList;
    }

    // helper method that loads the json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
