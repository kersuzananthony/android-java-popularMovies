package com.kersuzananthony.popularmovies.utilities;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.kersuzananthony.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        StringBuilder tempBuffer = new StringBuilder();

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int charRead;
            char[] inputBuffer = new char[500];

            while (true) {
                charRead = inputStreamReader.read(inputBuffer);

                if (charRead <= 0) {
                    break; // Break the loop
                }

                tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
            }

            Log.d(TAG, tempBuffer.toString());

            return tempBuffer.toString();
        } finally {
            urlConnection.disconnect();
        }
    }
}
