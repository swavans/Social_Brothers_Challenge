package com.developer.sascha.social_brothers_challenge.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Connects to The Internet with the Supplied URL and Executes a HTTP GET
 * Returns the Output in JSON
 * Implement AsyncResponse to acces the data
 */
public class BaconRetriever extends AsyncTask<String, String, String> {
    private AsyncResponse urlResponse = null;

    public void setUrlResponse(AsyncResponse urlResponse) {
        this.urlResponse = urlResponse;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                try {
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                response = "ERROR HTTP";

            }
            conn.disconnect();


        } catch (MalformedURLException e) {
            response = "ERROR URL";
            e.printStackTrace();

        } catch (ProtocolException e) {
            response = "ERROR PROTOCOL";
            e.printStackTrace();
        } catch (IOException e) {
            response = "ERROR IO";
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        if (!s.contains("ERROR")) {
            urlResponse.processFinished(s);
        }
        super.onPostExecute(s);
    }
}
