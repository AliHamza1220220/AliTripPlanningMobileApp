// Name : Ali Feras Sudqi Hamza
// ID : 1220220
// Sec : Sec #1

package com.example.alitripplanningmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public EditText edittextMainSearch;
    public RecyclerView recyclerviewMainTrip;
    public Button buttonMainAdd;

    public TripStore store;
    public TripAdapter adapter;
    public List<Trip> allTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittextMainSearch = findViewById(R.id.edittextMainSearch);
        recyclerviewMainTrip = findViewById(R.id.recyclerviewMainTrip);
        buttonMainAdd = findViewById(R.id.buttonMainAdd);

        store = new TripStore(this);
        allTrips = store.loadTrips();

        adapter = new TripAdapter(this, allTrips);
        recyclerviewMainTrip.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewMainTrip.setAdapter(adapter);

        buttonMainAdd.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
            startActivity(intent);
        });

        edittextMainSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filterTrips(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

    public void filterTrips(String string) {

        List<Trip> filtered = new ArrayList<>();

        for (int i = 0; i < allTrips.size(); i++) {

            Trip trip = allTrips.get(i);

            if (trip.getTitle().toLowerCase().contains(string.toLowerCase()) || trip.getSourceLocation().toLowerCase().contains(string.toLowerCase()) || trip.getDestinationLocation().toLowerCase().contains(string.toLowerCase())) {

                filtered.add(trip);
            }

        }

        adapter.updateList(filtered);
    }

    // life cycle manange
    @Override
    protected void onResume() {

        super.onResume();

        allTrips = store.loadTrips();
        adapter.updateList(allTrips);
    }

}