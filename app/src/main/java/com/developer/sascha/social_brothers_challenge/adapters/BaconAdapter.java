package com.developer.sascha.social_brothers_challenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.sascha.social_brothers_challenge.R;
import com.developer.sascha.social_brothers_challenge.data.Bacon;
import com.developer.sascha.social_brothers_challenge.holders.BaconHolder;

import java.util.ArrayList;


/**
 * This Adapter Connects the Bacon Object to the BaconViewHolder
 */
public class BaconAdapter extends RecyclerView.Adapter<BaconHolder> {

    private ArrayList<Bacon> mPiecesOfBacon;

    public BaconAdapter(ArrayList<Bacon> piecesOfBacon) {
        mPiecesOfBacon = piecesOfBacon;
    }

    public void setmPiecesOfBacon(ArrayList<Bacon> mPiecesOfBacon) {
        this.mPiecesOfBacon = mPiecesOfBacon;
        notifyDataSetChanged();
    }

    @Override
    public BaconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bacon_card, parent, false);
        return new BaconHolder(view);
    }

    @Override
    public void onBindViewHolder(BaconHolder holder, int position) {
        holder.getmBaconView().setText(mPiecesOfBacon.get(position).getmBacon());
    }

    @Override
    public int getItemCount() {
        if (mPiecesOfBacon != null)
            return mPiecesOfBacon.size();
        return 0;
    }
}
