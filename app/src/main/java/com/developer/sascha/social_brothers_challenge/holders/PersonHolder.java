package com.developer.sascha.social_brothers_challenge.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.sascha.social_brothers_challenge.R;

/**
 * Holder Containing the Views for a PersonCard
 */
public class PersonHolder extends RecyclerView.ViewHolder {
    private TextView name, surname, phone;
    private ImageView imageView;

    public PersonHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.card_name);
        surname = (TextView)itemView.findViewById(R.id.card_surname);
        phone = (TextView) itemView.findViewById(R.id.card_phone);
        imageView = (ImageView) itemView.findViewById(R.id.card_image);
    }

    public TextView getName() {
        return name;
    }

    public TextView getSurname() {
        return surname;
    }

    public TextView getPhone() {
        return phone;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
