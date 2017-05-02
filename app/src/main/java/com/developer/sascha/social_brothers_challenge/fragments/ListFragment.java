package com.developer.sascha.social_brothers_challenge.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.sascha.social_brothers_challenge.R;
import com.developer.sascha.social_brothers_challenge.tasks.AsyncResponse;
import com.developer.sascha.social_brothers_challenge.tasks.Bacon;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AsyncResponse {

    private TextView textView;

    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        textView = (TextView) rootView.findViewById(R.id.baconTest);


        Bacon b = new Bacon();
        b.setUrlResponse(this);
        b.execute("https://baconipsum.com/api/?type=all-meat");

        return rootView;
    }


    @Override
    public void processFinished(String output) {
        try {
            JSONArray JSONOutput = new JSONArray(output);
            textView.setText(JSONOutput.getString(0) + " BACON NUMBER 2" + JSONOutput.getString(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
