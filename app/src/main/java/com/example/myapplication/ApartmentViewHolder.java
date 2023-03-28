package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ApartmentViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public ImageView avatar;
    public TextView location;
    public TextView contact;

    public ApartmentViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        avatar = itemView.findViewById(R.id.avatar);
        location = itemView.findViewById(R.id.location);
        contact = itemView.findViewById(R.id.contact);
    }
}