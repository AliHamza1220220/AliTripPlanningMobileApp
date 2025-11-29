// Name : Ali Feras Sudqi Hamza
// ID : 1220220
// Sec : Sec #1

package com.example.alitripplanningmobileapp;

import java.util.*;
public class Trip {

    public String title;
    public String sourceLocation;
    public String destinationLocation;
    public long date;
    public String priority;
    public String notes;

    public Trip() {


    }

    public Trip(String title, String sourceLocation, String destinationLocation, long date, String priority, String notes) {

        this.title = title;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.date = date;
        this.priority = priority;
        this.notes = notes;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getSourceLocation() {

        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {

        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {

        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {

        this.destinationLocation = destinationLocation;
    }

    public long getDate() {

        return date;
    }

    public void setDate(long date) {

        this.date = date;
    }

    public String getPriority() {

        return priority;
    }

    public void setPriority(String priority) {

        this.priority = priority;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {

        this.notes = notes;
    }

    @Override
    public String toString() {

        return "Trip{" +
                "title='" + title + '\'' +
                ", sourceLocation='" + sourceLocation + '\'' +
                ", destinationLocation='" + destinationLocation + '\'' +
                ", date=" + date +
                ", priority='" + priority + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}