package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AptDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt_detail);

        Bundle b = getIntent().getExtras();
        Apartment user = (Apartment) b.getSerializable("Apartment");

        ImageView avatar = findViewById(R.id.avatar);
        TextView name = findViewById(R.id.location);
        TextView email = findViewById(R.id.contact);

        avatar.setImageResource(user.getImage());
        name.setText(user.getLocation());
        email.setText(user.getContact());

    }
}