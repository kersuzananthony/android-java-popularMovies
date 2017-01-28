package com.kersuzananthony.popularmovies.models;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {

    /* Constant for getting JSON Info */
    final String JSON_TITLE = "title";
    final String JSON_ORIGINAL_TITLE = "original_title";
    final String JSON_OVERVIEW = "overview";
    final String JSON_ADULT = "adult";
    final String JSON_POSTER_PATH = "poster_path";

    /* Private fields */
    private String mOriginalTitle;
    private String mTitle;
    private String mOverview;
    private boolean mIsAdult;
    private String mPosterPath;

    /** Constructor
     *
     * @param movieJsonObject
     * @throws JSONException
     */
    public Movie(JSONObject movieJsonObject) throws JSONException {
        this.mTitle = movieJsonObject.getString(JSON_TITLE);
        this.mOriginalTitle = movieJsonObject.getString(JSON_ORIGINAL_TITLE);
        this.mOverview = movieJsonObject.getString(JSON_OVERVIEW);
        this.mIsAdult = movieJsonObject.getBoolean(JSON_ADULT);
        this.mPosterPath = movieJsonObject.getString(JSON_POSTER_PATH);
    }

    protected Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mTitle = in.readString();
        mOverview = in.readString();
        mIsAdult = in.readByte() != 0;
        mPosterPath = in.readString();
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public boolean isAdult() {
        return mIsAdult;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    @Override
    public String toString() {
        return "Movie Title: " + this.mTitle + "\n"
                + "Movie Overview: " + this.mOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalTitle);
        dest.writeString(mTitle);
        dest.writeString(mOverview);
        dest.writeString(mPosterPath);
        dest.writeInt(mIsAdult ? 1 : 0);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
