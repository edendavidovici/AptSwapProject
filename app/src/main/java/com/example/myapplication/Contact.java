package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Contact extends AppCompatActivity {

    GoogleSignInAccount account;
    MessageAdapter adapter;

    RequestQueue mRequestQue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mRequestQue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            account = (GoogleSignInAccount)extras.get("user");
        }

        TextView welcome = findViewById(R.id.welcomeTV);
        welcome.setText("Welcome, " + account.getDisplayName());

        ImageView userImage = findViewById(R.id.mainUserImage);
        Glide.with(this).load(account.getPhotoUrl()).into(userImage);

        adapter = new MessageAdapter(account.getId());
        RecyclerView recycler = findViewById(R.id.chatRV);
        recycler.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),1);
        recycler.setLayoutManager(manager);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                recycler.smoothScrollToPosition(adapter.getItemCount()-1);
            }
        });

        recycler.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (adapter.getItemCount()>0) {
                    recycler.smoothScrollToPosition(adapter.getItemCount()-1);
                }
            }
        });

        recycler.setAdapter(adapter);

        FloatingActionButton btn = findViewById(R.id.addMessageBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.messageET);
                chatMessage m = new chatMessage(account.getPhotoUrl().toString(),account.getDisplayName(),account.getId(),text.getText().toString());
                adapter.addMessage(m);
                text.setText("");
                sendCloudMessage(m);
            }
        });

    }

    private void sendCloudMessage(chatMessage m) {
        String serverKey = "AAAAgU33a_4:APA91bH-6UERMLjeELi6mGYSyE7cZXWPmUp4aAxHLnR1BRxHYY7jx5-nY_-JaAOPdBd5U5xeNMwyfG75efTWrQX6b6WOadi7uLTJ_rb53vb6u-L4RCzt2-6w-EvvNdXsu22DASaST-8_";
        String topic = "chat";

        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/" + topic);
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", m.userName);
            notificationObj.put("body", m.message);
            json.put("notification", notificationObj);

            String URL = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                    json,
                    response -> Log.d("MUR", "onResponse: " + response.toString()),
                    error -> Log.d("MUR", "onError: " + error.networkResponse)
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + serverKey);
                    return header;
                }
            };

            mRequestQue.add(request);

        } catch (Exception e) {

        }
    }
}