package com.kersuzananthony.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent startSettingsActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivityIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
