package com.kersuzananthony.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kersuzananthony.popularmovies.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int MOVIE_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtils.builder(this, NetworkUtils.URL_BASE_MOVIES, "top_rated");
    }
}
