package com.example.alitripplanningmobileapp;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TripStore {
    public static final String PREF_NAME = "trip_prefs";
    public static final String KEY_TRIPS = "trip_json";
    public SharedPreferences sharedPreferences;

    public TripStore(Context context) {

        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveTrips(List<Trip> trips) {

        JSONArray arr = new JSONArray();

        for (int i = 0; i < trips.size(); i++) {

            Trip trip = trips.get(i);
            JSONObject tripObject = new JSONObject();

            try {

                tripObject.put("title", trip.getTitle());
                tripObject.put("sourceLocation", trip.getSourceLocation());
                tripObject.put("destinationLocation", trip.getDestinationLocation());
                tripObject.put("date", trip.getDate());
                tripObject.put("priority", trip.getPriority());
                tripObject.put("notes", trip.getNotes());

                arr.put(tripObject);
            }

            catch (JSONException e) {

                System.out.println(e.getMessage());
            }

        }

        sharedPreferences.edit().putString(KEY_TRIPS, arr.toString()).apply();
    }

    public List<Trip> loadTrips() {

        String json = sharedPreferences.getString(KEY_TRIPS, "[]");
        List<Trip> list = new ArrayList<>();

        try {

            JSONArray arr = new JSONArray(json);

            for (int i = 0; i < arr.length(); i++) {

                JSONObject tripObject = arr.getJSONObject(i);
                Trip trip = new Trip(tripObject.optString("title"), tripObject.optString("sourceLocation"), tripObject.optString("destinationLocation"),  tripObject.optLong("date"), tripObject.optString("priority"), tripObject.optString("notes"));
                list.add(trip);
            }
        }

        catch (JSONException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    public boolean addTrip(Trip trip) {

        List<Trip> list = loadTrips();

        for(int i = 0 ; i < list.size(); i++){

            if(list.get(i).getTitle().equalsIgnoreCase(trip.getTitle())) {

                return false;
            }
        }

        list.add(trip);
        saveTrips(list);
        return true;
    }

    public boolean updateTrip(Trip trip) {

        List<Trip> list = loadTrips();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getTitle().equalsIgnoreCase(trip.getTitle())) {

                list.set(i, trip);
                saveTrips(list);
                return true;
            }

        }

        return false;
    }

    public boolean deleteTrip(String title) {

        List<Trip> list = loadTrips();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getTitle().equalsIgnoreCase(title)) {

                list.remove(i);
                saveTrips(list);
                return true;
            }

        }

        return false;

    }

}