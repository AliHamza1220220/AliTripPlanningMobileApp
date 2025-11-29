package com.example.alitripplanningmobileapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;

public class AddEditActivity extends AppCompatActivity {

    public EditText edittextAddEditUserName;
    public EditText edittextAddEditSourceLocation;
    public EditText edittextAddEditDestinationLocation;
    public DatePicker datepickerAddEditDate;
    public RadioGroup radiogroupAddEditPriority;
    public EditText edittextAddEditNotes;
    public Button buttonAddEditSave;

    public TripStore store;
    public String tripTitle;
    public Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edittextAddEditUserName = findViewById(R.id.edittextAddEditUserName);
        edittextAddEditSourceLocation = findViewById(R.id.edittextAddEditSourceLocation);
        edittextAddEditDestinationLocation = findViewById(R.id.edittextAddEditDestinationLocation);
        datepickerAddEditDate = findViewById(R.id.datepickerAddEditDate);
        radiogroupAddEditPriority = findViewById(R.id.radiogroupAddEditPriority);
        edittextAddEditNotes = findViewById(R.id.edittextAddEditNotes);
        buttonAddEditSave = findViewById(R.id.buttonAddEditSave);

        store = new TripStore(this);

        tripTitle = getIntent().getStringExtra("title");

        if (tripTitle != null) {

            loadTrip(tripTitle);
            fillFields();
        }

        if (savedInstanceState != null) {

            restoreState(savedInstanceState);
        }

        buttonAddEditSave.setOnClickListener(v -> {

            saveTrip();
        });

    }

    public void loadTrip(String title) {

        List<Trip> trips = store.loadTrips();

        for (int i = 0; i < trips.size(); i++) {

            Trip t = trips.get(i);

            if (t.getTitle().equals(title)) {

                currentTrip = t;
                break;
            }

        }

    }

    public void fillFields() {

        if (currentTrip == null) {

            return;
        }

        edittextAddEditUserName.setText(currentTrip.getTitle());
        edittextAddEditSourceLocation.setText(currentTrip.getSourceLocation());
        edittextAddEditDestinationLocation.setText(currentTrip.getDestinationLocation());
        edittextAddEditNotes.setText(currentTrip.getNotes());

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTrip.getDate());
        datepickerAddEditDate.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        if ("High".equals(currentTrip.getPriority())) {

            radiogroupAddEditPriority.check(R.id.radiobuttonAddEditHigh);
        }

        else if ("Medium".equals(currentTrip.getPriority())) {

            radiogroupAddEditPriority.check(R.id.radiobuttonAddEditMedium);
        }

        else {

            radiogroupAddEditPriority.check(R.id.radiobuttonAddEditLow);
        }

    }

    public void saveTrip() {

        String title = edittextAddEditUserName.getText().toString().trim();
        String source = edittextAddEditSourceLocation.getText().toString().trim();
        String destination = edittextAddEditDestinationLocation.getText().toString().trim();
        String notes = edittextAddEditNotes.getText().toString().trim();

        Calendar cal = Calendar.getInstance();
        cal.set(datepickerAddEditDate.getYear(), datepickerAddEditDate.getMonth(), datepickerAddEditDate.getDayOfMonth());
        long date = cal.getTimeInMillis();

        String priority = "Low";

        int selectedId = radiogroupAddEditPriority.getCheckedRadioButtonId();

        if (selectedId == R.id.radiobuttonAddEditHigh) {

            priority = "High";
        }

        else if (selectedId == R.id.radiobuttonAddEditMedium) {

            priority = "Medium";
        }

        if (currentTrip == null) {

            Trip trip = new Trip(title, source, destination, date, priority, notes);
            store.addTrip(trip);
        }

        else {

            Trip trip = new Trip(title, source, destination, date, priority, notes);
            store.updateTrip(trip);
        }

        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putString("title", edittextAddEditUserName.getText().toString());
        outState.putString("sourcelocation", edittextAddEditSourceLocation.getText().toString());
        outState.putString("destinationlocation", edittextAddEditDestinationLocation.getText().toString());
        outState.putInt("year", datepickerAddEditDate.getYear());
        outState.putInt("month", datepickerAddEditDate.getMonth());
        outState.putInt("day", datepickerAddEditDate.getDayOfMonth());
        outState.putInt("priority", radiogroupAddEditPriority.getCheckedRadioButtonId());
        outState.putString("notes", edittextAddEditNotes.getText().toString());
    }

    public void restoreState(Bundle savedInstanceState) {

        edittextAddEditUserName.setText(savedInstanceState.getString("title", ""));
        edittextAddEditSourceLocation.setText(savedInstanceState.getString("sourcelocation", ""));
        edittextAddEditDestinationLocation.setText(savedInstanceState.getString("destinationlocation", ""));
        datepickerAddEditDate.updateDate(savedInstanceState.getInt("year"), savedInstanceState.getInt("month"), savedInstanceState.getInt("day"));
        int priorityId = savedInstanceState.getInt("priority");
        edittextAddEditNotes.setText(savedInstanceState.getString("notes", ""));

        if (priorityId != -1) {

            radiogroupAddEditPriority.check(priorityId);
        }

    }

}