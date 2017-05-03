package com.developer.sascha.social_brothers_challenge.fragments;


import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.sascha.social_brothers_challenge.R;
import com.developer.sascha.social_brothers_challenge.adapters.PersonAdapter;
import com.developer.sascha.social_brothers_challenge.data.Person;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
    private PersonAdapter mPersonAdapter;
    private String mOldPersonConfig = null;
    public CardFragment() {


    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mOldPersonConfig.equals(getPersonConfig())) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mPersonAdapter.setPersons(createPersonList(Integer.parseInt(preferences.getString("amount_of_cards", "10"))));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());


        mPersonAdapter = new PersonAdapter(createPersonList(Integer.parseInt(preferences.getString("amount_of_cards", "10"))));
        recyclerView.setAdapter(mPersonAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    public String getPersonConfig() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return preferences.getString("gender_card", "0") + preferences.getString("name_of_cards", "Admini") + preferences.getString("surname_of_cards", "de Beta Tester") + preferences.getString("phone_of_cards", "+3160000000") + preferences.getString("amount_of_cards", "10");
    }

    public ArrayList<Person> createPersonList(int i) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ArrayList<Person> persons = new ArrayList<>();
        mOldPersonConfig = getPersonConfig();
        for (int j = 0; j < i; j++) {
            Person p;
            int number = j + 1;
            if (preferences.getString("gender_card", "0").equals("0")) {
                p = new Person(preferences.getString("name_of_cards", "Admini") + " " + number, preferences.getString("surname_of_cards", "de Beta Tester"), preferences.getString("phone_of_cards", "+3160000000"), BitmapFactory.decodeResource(getResources(), R.drawable.fdummy));
            } else {
                p = new Person(preferences.getString("name_of_cards", "Admini") + " " + number, preferences.getString("surname_of_cards", "de Beta Tester"), preferences.getString("phone_of_cards", "+3160000000"), BitmapFactory.decodeResource(getResources(), R.drawable.dummy));
            }
            persons.add(p);
        }
        return persons;
    }

}
