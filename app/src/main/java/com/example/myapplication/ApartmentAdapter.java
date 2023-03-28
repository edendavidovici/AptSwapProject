package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentViewHolder> {
    List<Apartment> Apartments;
    public ApartmentAdapter(List<Apartment> apartments) {
        Apartments = apartments;
    }
    public void addApartment(Apartment apartment) {
        Apartments.add(apartment);
        notifyDataSetChanged();
        DataPersistencyHelper.storeData(Apartments);
    }

    public void deleteUser(int position) {
        Apartments.remove(position);
        notifyDataSetChanged();
        DataPersistencyHelper.storeData(Apartments);
    }

    @NonNull
    @Override
    public ApartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apartment,parent,false);
        ApartmentViewHolder viewHolder = new ApartmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentViewHolder holder, int position) {
        Apartment apartment = Apartments.get(position);
        holder.avatar.setImageResource(apartment.getImage());
        holder.location.setText(apartment.getLocation());
        holder.contact.setText(apartment.getContact());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AptDetailActivity.class);
                intent.putExtra("Apartment", apartment);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) view.getContext(),
                        holder.avatar,
                        "AvatarTransition"
                );
                view.getContext().startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Apartments.size();
    }
}