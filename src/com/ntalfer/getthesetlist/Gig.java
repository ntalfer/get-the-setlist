package com.ntalfer.getthesetlist;

import java.util.ArrayList;

/**
 * Created by nicolas on 25/12/13.
 */
public class Gig {
    private String eventDate;
    //private String artistName;
    private String country;
    private String city;
    private String venue;
    //private Setlist setlist;
    private ArrayList<String> songs;

    public Gig(String eventDate, String venue, String city, String country, ArrayList<String> songs)
    {
        this.eventDate = eventDate;
        this.venue = venue;
        this.city = city;
        this.country = country;
        this.songs = songs;
    }
}
