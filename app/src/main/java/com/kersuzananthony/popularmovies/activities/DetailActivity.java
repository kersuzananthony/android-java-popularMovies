package com.kersuzananthony.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kersuzananthony.popularmovies.R;
import com.kersuzananthony.popularmovies.models.Movie;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get movie stored in the intent
        Intent startDetailActivityIntent = getIntent();
        if (startDetailActivityIntent != null) {
            if (startDetailActivityIntent.hasExtra(MainActivity.INTENT_MOVIE)) {
                mMovie = startDetailActivityIntent.getParcelableExtra(MainActivity.INTENT_MOVIE);
                Log.d(TAG, mMovie.toString());
            }
        }
    }
}
