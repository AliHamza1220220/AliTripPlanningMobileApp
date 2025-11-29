package com.example.alitripplanningmobileapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TaskViewHolder> {

    private Context context;
    private List<Trip> trips;

    public TripAdapter(Context context, List<Trip> trips) {

        this.context = context;
        this.trips = trips;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        Trip trip = trips.get(position);

        holder.textviewTitle.setText(trip.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateStr = sdf.format(trip.getDate());

        String subInfo = trip.getSourceLocation() + " to " + trip.getDestinationLocation() + " • " + dateStr + " • " + trip.getPriority();
        holder.textviewSub.setText(subInfo);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", trip.getTitle());
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {

            TripStore store = new TripStore(context);
            store.deleteTrip(trip.getTitle());

            trips.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Trip deleted", Toast.LENGTH_LONG).show();

            return true;
        });
    }

    @Override
    public int getItemCount() {

        return trips.size();
    }

    public void updateList(List<Trip> newTrip) {

        trips = newTrip;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewTask;
        TextView textviewTitle;
        TextView textviewSub;

        public TaskViewHolder(View itemView) {

            super(itemView);

            imageviewTask = itemView.findViewById(R.id.imageviewTask);
            textviewTitle = itemView.findViewById(R.id.textviewTitle);
            textviewSub = itemView.findViewById(R.id.textviewSub);
        }

    }

}