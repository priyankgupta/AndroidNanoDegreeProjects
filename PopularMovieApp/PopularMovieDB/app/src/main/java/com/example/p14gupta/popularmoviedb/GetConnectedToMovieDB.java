package com.example.p14gupta.popularmoviedb;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetConnectedToMovieDB extends AsyncTask<String, String, JSONObject > {

    private final String LOG_TAG = PopMovieMainPage.class.getSimpleName();

    @Override
    protected JSONObject doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        JSONObject response = new JSONObject ();

        try {
            url = new URL(params[0]);
            Log.i(LOG_TAG, url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(50000);
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseString = readStream(urlConnection.getInputStream());
                Log.i(LOG_TAG, responseString);
                response = new JSONObject (responseString);
            } else {
                Log.e(LOG_TAG, "Error Received with Response code:" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return response;
    }

    @Override
    protected void onPostExecute(JSONObject  JsonObject ) {
        Log.i(LOG_TAG, "post execute");
        super.onPostExecute(JsonObject);
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}

