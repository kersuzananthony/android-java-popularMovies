package com.kersuzananthony.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kersuzananthony.popularmovies.R;
import com.kersuzananthony.popularmovies.models.Movie;
import com.kersuzananthony.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private Movie mMovie;

    /* User Interface class member */
    private TextView mMovieTitleTextView;
    private ImageView mMoviePosterImageView;
    private TextView mMovieDateTextView;
    private TextView mMovieDurationTextView;
    private TextView mMovieRateTextView;
    private TextView mMovieOverviewTextView;

    /**
     * OnCreate lifecycle
     * We check if the intent contains the Movie item and assigns it to the member variable
     *
     * @param savedInstanceState
     */
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

        /* Link XML UI element to JAVA class */
        mMovieTitleTextView = (TextView) findViewById(R.id.activity_detail_tv_movie_title);
        mMoviePosterImageView = (ImageView) findViewById(R.id.activity_detail_iv_movie_poster);
        mMovieDateTextView = (TextView) findViewById(R.id.activity_detail_tv_movie_date);
        mMovieDurationTextView = (TextView) findViewById(R.id.activity_detail_tv_movie_duration);
        mMovieRateTextView = (TextView) findViewById(R.id.activity_detail_tv_movie_rate);
        mMovieOverviewTextView = (TextView) findViewById(R.id.activity_detail_tv_movie_overview);

        setupLayout();
    }

    /**
     * Methods for updating the UI according to the Movie saved in the member variable
     */
    private void setupLayout() {
        if (mMovie != null) {
            mMovieTitleTextView.setText(mMovie.getTitle());
            //mMovieDateTextView.setText(mMovie.getReleaseDate());
            mMovieDurationTextView.setText(String.valueOf(120) + "min");
            mMovieRateTextView.setText(String.valueOf(mMovie.getVoteAverage() + "/10"));
            mMovieOverviewTextView.setText(mMovie.getOverview());

            Picasso.with(mMoviePosterImageView.getContext())
                    .load(NetworkUtils.buildUri(this, NetworkUtils.URL_BASE_IMAGE, mMovie.getPosterPath()))
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder_error)
                    .fit()
                    .centerCrop()
                    .into(mMoviePosterImageView);
        }
    }

    /**
     * Create menu from XML file
     *
     * @param menu Menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu, menu);

        return true;
    }

    /**
     * Answers to the menu select event
     *
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent startSettingsActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivityIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
