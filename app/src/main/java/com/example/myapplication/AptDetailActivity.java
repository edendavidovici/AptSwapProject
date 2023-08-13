package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AptDetailActivity extends AppCompatActivity {
    GoogleSignInAccount account;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt_detail);

        Bundle b = getIntent().getExtras();
        Apartment user = (Apartment) b.getSerializable("Apartment");

        ImageView avatar = findViewById(R.id.avatar);
        TextView name = findViewById(R.id.location);
        TextView owner = findViewById(R.id.owner);


        avatar.setImageResource(user.getImage());
        name.setText(user.getLocation());
        owner.setText(user.getOwner());



    }
}