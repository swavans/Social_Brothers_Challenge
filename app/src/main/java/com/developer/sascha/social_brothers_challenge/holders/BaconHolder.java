package com.developer.sascha.social_brothers_challenge.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developer.sascha.social_brothers_challenge.R;

/**
 *  Simple Holder Containing one Textview
 */

public class BaconHolder extends RecyclerView.ViewHolder {
    private TextView mBaconView;

    public BaconHolder(View itemView) {
        super(itemView);
        mBaconView = (TextView) itemView.findViewById(R.id.baconView);
    }

    public TextView getmBaconView() {
        return mBaconView;
    }
}
