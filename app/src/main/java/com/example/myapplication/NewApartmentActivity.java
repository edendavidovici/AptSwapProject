package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewApartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_apartment);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.location);
                EditText email = findViewById(R.id.contact);

                Apartment newApartment = new Apartment(R.drawable.avatar6,name.getText().toString(),email.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("apartment",newApartment);
                setResult(1,intent);
                finish();
            }
        });
    }
}