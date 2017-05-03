package com.developer.sascha.social_brothers_challenge.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.sascha.social_brothers_challenge.R;
import com.developer.sascha.social_brothers_challenge.adapters.BaconAdapter;
import com.developer.sascha.social_brothers_challenge.data.Bacon;
import com.developer.sascha.social_brothers_challenge.tasks.AsyncResponse;
import com.developer.sascha.social_brothers_challenge.tasks.BaconRetriever;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AsyncResponse, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mBaconRecycler;
    private BaconRetriever mBaconRetriever;
    private ArrayList<Bacon> mBaconPieces;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BaconAdapter mBaconAdapter;
    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        mBaconRecycler = (RecyclerView) rootView.findViewById(R.id.baconRecycler);
        mBaconRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return rootView;
    }


    @Override
    public void processFinished(String output) {
        try {
            JSONArray JSONOutput = new JSONArray(output);
            mBaconPieces = new ArrayList<>();
            for (int i = 0; i < JSONOutput.length(); i++) {
                mBaconPieces.add(new Bacon(JSONOutput.getString(i)));
            }
            mBaconAdapter.setmPiecesOfBacon(mBaconPieces);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBaconPieces == null) {
            mSwipeRefreshLayout.setRefreshing(true);
            mBaconAdapter = new BaconAdapter(mBaconPieces);
            mBaconRecycler.setAdapter(mBaconAdapter);
            getBacon();
        } else {
            mBaconAdapter = new BaconAdapter(mBaconPieces);
            mBaconRecycler.setAdapter(mBaconAdapter);
        }
    }

    public void getBacon() {
        mBaconRetriever = new BaconRetriever();
        mBaconRetriever.setUrlResponse(this);
        mBaconRetriever.execute("https://baconipsum.com/api/?type=all-meat");
    }

    @Override
    public void onRefresh() {
        getBacon();
    }
}
