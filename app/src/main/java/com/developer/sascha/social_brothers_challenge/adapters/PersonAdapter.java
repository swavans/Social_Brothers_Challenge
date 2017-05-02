package com.developer.sascha.social_brothers_challenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.sascha.social_brothers_challenge.R;
import com.developer.sascha.social_brothers_challenge.data.Person;
import com.developer.sascha.social_brothers_challenge.holders.PersonHolder;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {
    private ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> persons) {
        this.persons = persons;

    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_card, parent, false);

        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {
        holder.getName().setText(persons.get(position).getName());
        holder.getSurname().setText(persons.get(position).getSurname());
        holder.getPhone().setText(persons.get(position).getPhone());
        holder.getImageView().setImageBitmap(persons.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
