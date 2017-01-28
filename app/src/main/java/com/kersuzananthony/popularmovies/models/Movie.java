package com.kersuzananthony.popularmovies.models;


import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    /* Constant for getting JSON Info */
    final String JSON_TITLE = "title";
    final String JSON_ORIGINAL_TITLE = "original_title";
    final String JSON_OVERVIEW = "overview";
    final String JSON_ADULT = "adult";
    final String JSON_POSTER_PATH = "poster_path";

    /* Private fields */
    private String originalTitle;
    private String title;
    private String overview;
    private boolean isAdult;
    private String posterPath;

    /** Constructor
     *
     * @param movieJsonObject
     * @throws JSONException
     */
    public Movie(JSONObject movieJsonObject) throws JSONException {
        this.title = movieJsonObject.getString(JSON_TITLE);
        this.originalTitle = movieJsonObject.getString(JSON_ORIGINAL_TITLE);
        this.overview = movieJsonObject.getString(JSON_OVERVIEW);
        this.isAdult = movieJsonObject.getBoolean(JSON_ADULT);
        this.posterPath = movieJsonObject.getString(JSON_POSTER_PATH);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return "Movie Title: " + this.title + "\n"
                + "Movie Overview: " + this.overview;
    }
}
