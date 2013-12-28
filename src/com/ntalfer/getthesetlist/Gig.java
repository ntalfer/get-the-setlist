package com.ntalfer.getthesetlist;

import java.util.ArrayList;

public class Gig {
    private String eventDate;
    //private String artistName;
    private String country;
    private String city;
    private String venue;

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    private ArrayList<String> songs;

    public String getShortInfo() {
        StringBuilder st = new StringBuilder();
        st.append(this.venue).append(", ").append(this.city).append(", ").append(this.country).append(" - ").append(this.eventDate);
        return st.toString();
    }

    public Gig(String eventDate, String venue, String city, String country, ArrayList<String> songs)
    {
        this.eventDate = eventDate;
        this.venue = venue;
        this.city = city;
        this.country = country;
        this.songs = songs;
    }
}
