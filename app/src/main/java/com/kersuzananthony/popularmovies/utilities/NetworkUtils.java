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

    /**
     * A helper which help us to build the endpoint URL.
     *
     * @param context
     * @param baseUrl       Either URL_BASE_MOVIES or URL_BASE_IMAGE
     * @param pathOption    It can contain sorting option OR image path
     * @return URL          URL for making HTTP Request
     */
    public static URL builder(Context context, String baseUrl, String pathOption) {
        Uri uri = buildUri(context, baseUrl, pathOption);

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
     * A helper which help us to build the endpoint Uri.
     *
     * @param context
     * @param baseUrl       Either URL_BASE_MOVIES or URL_BASE_IMAGE
     * @param pathOption    It can contain sorting option OR image path
     * @return URL          URL for making HTTP Request
     */
    public static Uri buildUri(Context context, String baseUrl, String pathOption) {
        Uri.Builder uriBuilder = Uri.parse(baseUrl).buildUpon();

        if (pathOption != null && !pathOption.equals("")) {
            uriBuilder.appendEncodedPath(pathOption);
        }

        if (baseUrl.equals(NetworkUtils.URL_BASE_MOVIES)) {
            uriBuilder.appendQueryParameter(QUERY_API_PARAM, context.getString(R.string.db_movie_api_key));
        }

        return uriBuilder.build();
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        // Prevents request if the user's device is offline
        if (!NetworkUtils.isDeviceOnline()) {
            Log.d(TAG, "User device is offline");
            return null;
        }

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

            Log.v(TAG, tempBuffer.toString());

            return tempBuffer.toString();
        } finally {
            urlConnection.disconnect();
        }
    }


    /**
     * Methods that check if the device is online
     * See details on the implementation on StackOverflow website
     * http://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
     *
     * @return boolean
     */
    public static boolean isDeviceOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
