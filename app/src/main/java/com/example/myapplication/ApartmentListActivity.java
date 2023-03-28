package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ApartmentListActivity extends AppCompatActivity {

    ApartmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_list);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        newApartmentResult(data);
                    }
                }
        );
        DataPersistencyHelper.context = getApplicationContext();
        List<Apartment> users = DataPersistencyHelper.loadData();



        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ApartmentAdapter(users);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        helper.attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NewApartmentActivity.class);
                activityResultLauncher.launch(intent);
            }
        });


    }

    private void newApartmentResult(Intent data) {
        Bundle b = data.getExtras();
        Apartment apartment = (Apartment) b.getSerializable("apartment");
        adapter.addApartment(apartment);

    }
}