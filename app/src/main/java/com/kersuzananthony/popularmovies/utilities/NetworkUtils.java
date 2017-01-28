package com.kersuzananthony.popularmovies.utilities;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.kersuzananthony.popularmovies.R;

import java.net.MalformedURLException;
import java.net.URL;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static final String URL_BASE_MOVIES = "http://api.themoviedb.org/3/movie";

    public static final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";

    final static String QUERY_API_PARAM = "api_key";

    public static URL builder(Context context, String baseUrl, String sortOption) {
        Uri.Builder uriBuilder = Uri.parse(baseUrl).buildUpon();

        // Append sort parameter only if the base url is equal to base movie URL.
        // Add api_key if the base url is equal to base movie URL
        if (baseUrl.equals(NetworkUtils.URL_BASE_MOVIES)) {
            if (sortOption != null && !sortOption.equals("")) {
                uriBuilder.appendPath(sortOption);
            }

            uriBuilder.appendQueryParameter(QUERY_API_PARAM, context.getString(R.string.db_movie_api_key));
        }

        Uri uri = uriBuilder.build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Built URL " + url);

        return url;
    }
}
