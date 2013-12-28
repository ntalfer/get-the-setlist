package com.ntalfer.getthesetlist;

import android.util.Log;

import com.androidquery.util.XmlDom;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Tour {
    public static ArrayList<Gig> getAllGigs() throws IOException, SAXException {
        //private final static String API_key = "9f97968e-3e78-45f6-9257-1b1a25fd4898";
        ArrayList<Gig> gigs = new ArrayList<Gig>();

        try {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request = new HttpGet(Artist.getBlurTourUrl());

            HttpResponse response = client.execute(request);
            InputStream inputStream = response.getEntity().getContent();

            XmlDom xmlDom = new XmlDom(inputStream);

            for (XmlDom entry : xmlDom.children("setlist")) {
                String eventDate = entry.attr("eventDate");
                String venue = entry.tag("venue").attr("name");
                String city = entry.child("venue").tag("city").attr("name");
                String country = entry.child("venue").child("city").tag("country").attr("name");

                ArrayList<String> songs = new ArrayList();

                for (XmlDom song : xmlDom.children("set")) {
                    songs.add(song.attr("name"));
                }

                gigs.add(new Gig(eventDate, venue, city, country, songs));
            }

            //gigs.add(new Gig("1", "v", "c", "co", new ArrayList()));
            //gigs.add(new Gig("2", "v2", "c2", "co2", new ArrayList()));

            //System.out.println(gigs.toString());
            return gigs;
        } catch (Exception e) {
            Log.d("e", e.getMessage());
        } finally {
            return gigs;
        }
    }
}
