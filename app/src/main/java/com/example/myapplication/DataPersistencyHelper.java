package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class DataPersistencyHelper {

    public static Context context;

    public static void storeData(List<Apartment> apartments) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor etitor = sp.edit();
        String json = new Gson().toJson(apartments);
        etitor.putString("apartments",json);
        etitor.commit();
    }

    public static List<Apartment> loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString("users",null);
        if (json != null) {
            Type type = new TypeToken<List<Apartment>>(){}.getType();
            return new Gson().fromJson(json,type);
        } else {
            List<Apartment> apartments = new ArrayList<>();
            apartments.add(new Apartment(R.drawable.avatar1,"Madrid", "Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar2,"Paris","Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar3,"Haifa","Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar4,"Tel-Aviv","Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar5,"Los Angeles","Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar6,"Seoul", "Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar7,"Kyoto","Barack Obama"));
            apartments.add(new Apartment(R.drawable.avatar8,"Moscow","Barack Obama"));
            return apartments;
        }


    }

}