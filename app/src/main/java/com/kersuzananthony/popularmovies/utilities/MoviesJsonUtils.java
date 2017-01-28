package com.kersuzananthony.popularmovies.utilities;


import com.kersuzananthony.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoviesJsonUtils {

    public static ArrayList<Movie> getMoviesFromJson(String movieResultJson) throws JSONException {

        /* Movie information. Each movie's information is an element of the "results" array */
        final String MD_RESULT = "results";

        final String MD_SUCCESS = "success";

        ArrayList<Movie> movies = new ArrayList<>();

        // Convert movieResultJson to a JSONObject
        JSONObject movieJsonObject = new JSONObject(movieResultJson);

        /* Is there an error? */
        if (movieJsonObject.has(MD_SUCCESS)) {
            boolean statusCode = movieJsonObject.getBoolean(MD_SUCCESS);

            if (statusCode == false) {
                /* request failed */
                return null;
            }
        }

        JSONArray moviesJsonArray = movieJsonObject.getJSONArray(MD_RESULT);

        for (int i = 0; i < moviesJsonArray.length(); i++) {
            JSONObject currentMovieJSONObject = moviesJsonArray.getJSONObject(i);

            // Build Movie Object
            movies.add(i, new Movie(currentMovieJSONObject));
        }

        return movies;
    }


}
