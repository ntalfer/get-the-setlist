package com.ntalfer.getthesetlist;

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

    private ArrayList<Gig> gigs;

    //private final static String API_key = "9f97968e-3e78-45f6-9257-1b1a25fd4898";

    public Tour() {
        this.gigs = new ArrayList<Gig>();

        try {
            getAllGigs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Gig> getGigs() {
        return this.gigs;
    }

    private void getAllGigs() throws IOException, SAXException {
        int page = 1;
        int total = -1;

        do {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request = new HttpGet(Artist.getBlurTourUrl(page));

            HttpResponse response = client.execute(request);
            InputStream inputStream = response.getEntity().getContent();

            XmlDom xmlDom = new XmlDom(inputStream);

            if (total == -1) {
                total = Integer.parseInt(xmlDom.attr("total"));
            }

            for (XmlDom entry : xmlDom.children("setlist")) {
                String eventDate = entry.attr("eventDate");
                String venue = entry.tag("venue").attr("name");
                String city = entry.child("venue").tag("city").attr("name");
                String country = entry.child("venue").child("city").tag("country").attr("name");
                ArrayList<String> songs = new ArrayList();

                for (XmlDom set : entry.child("sets").children("set")) {
                    for (XmlDom song : set.children("song")) {
                        songs.add(song.attr("name"));
                    }
                }

                this.gigs.add(new Gig(eventDate, venue, city, country, songs));
            }

            page++;
        }
        while (this.gigs.size() < total);
    }
}
