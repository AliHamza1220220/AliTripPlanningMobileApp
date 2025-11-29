package com.example.alitripplanningmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    public TextView textviewDetailsTitle;
    public TextView textviewDetailsDescription;
    public TextView textviewDetailsNotesView;
    public Button buttonDetailsEdit;

    public TripStore store;
    public Trip currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textviewDetailsTitle = findViewById(R.id.textviewDetailsTitle);
        textviewDetailsDescription = findViewById(R.id.textviewDetailsDescription);
        textviewDetailsNotesView = findViewById(R.id.textviewDetailsNotesView);
        buttonDetailsEdit = findViewById(R.id.buttonDetailsEdit);

        store = new TripStore(this);

        String taskTitle = getIntent().getStringExtra("title");

        loadTrip(taskTitle);

        buttonDetailsEdit.setOnClickListener(v -> {

            Intent intent = new Intent(DetailsActivity.this, AddEditActivity.class);
            intent.putExtra("title", currentTask.getTitle());
            startActivity(intent);
        });
    }

    public void loadTrip(String title) {

        List<Trip> trips = store.loadTrips();

        for (int i = 0; i < trips.size(); i++) {

            Trip t = trips.get(i);

            if (t.getTitle().equals(title)) {

                currentTask = t;
                break;
            }
        }

        if (currentTask != null) {

            textviewDetailsTitle.setText(currentTask.getTitle());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateStr = sdf.format(currentTask.getDate());

            String subInfo = currentTask.getSourceLocation() + " to " + currentTask.getDestinationLocation() + " • " + dateStr + " • " + currentTask.getPriority();
            textviewDetailsDescription.setText(subInfo);

            textviewDetailsNotesView.setText(currentTask.getNotes());
        }

    }

}