package com.ntalfer.getthesetlist;

import android.os.AsyncTask;

import com.androidquery.util.XmlDom;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;

public class SetlistFetcher extends AsyncTask {

    //private final static String API_key = "9f97968e-3e78-45f6-9257-1b1a25fd4898";
    private ArrayList<Gig> gigs;

    @Override
    protected Object doInBackground(Object[] objects) {

        this.gigs = new ArrayList<Gig>();

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request = new HttpGet(Artist.getBlurTourUrl());

            HttpResponse response = client.execute(request);
            InputStream inputStream = response.getEntity().getContent();

            XmlDom xmlDom = new XmlDom(inputStream);

            for(XmlDom entry : xmlDom.children("setlist"))
            {
                String eventDate = entry.attr("eventDate");
                String venue = entry.tag("venue").attr("name");
                String city = entry.child("venue").tag("city").attr("name");
                String country = entry.child("venue").child("city").tag("country").attr("name");

                ArrayList<String> songs = new ArrayList();

                for(XmlDom song : xmlDom.children("set"))
                {
                    songs.add(song.attr("name"));
                }

                this.gigs.add(new Gig(eventDate, venue, city, country, songs));
            }

            System.out.println(this.gigs.toString());
            return this.gigs;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return this.gigs;
        }
    }

            /*@Override
    protected void onPostExecute(ArrayList<Gig> gigs) {
            musics = downloadedMusics;
            listView.setAdapter(new MusicAdapter(musics, getActivity()));
            super.onPostExecute(musics);
        }*/
}

             /*<setlist eventDate="09-11-2013" id="13c4a1ad" lastUpdated="2013-11-10T01:30:58.000+0000" tour="2013/2014 Festival Tour" versionId="7befb2d4">
        <artist disambiguation="" mbid="ba853904-ae25-4ebb-89d6-c44cfbd71bd2" name="Blur" sortName="Blur" tmid="740429">
            <url>http://www.setlist.fm/setlists/blur-13d6bd65.html</url>
        </artist>
        <venue id="23d4943f" name="Campo de Marte">
            <city id="6324358" name="São Paulo" state="São Paulo" stateCode="27">
                <coords lat="-23.6270250218409" long="-46.6350328065523"/>
                <country code="BR" name="Brazil"/>
            </city>
            <url>http://www.setlist.fm/venue/campo-de-marte-sao-paulo-brazil-23d4943f.html</url>
        </venue>*/
          /*              <sets>
            <set>
                <song name="Theme from Retro"/>
                <song name="Girls &amp; Boys"/>
                <song name="There's No Other Way"/>
                <song name="Beetlebum"/>
                <song name="Out of Time"/>
                <song name="Trimm Trabb"/>
                <song name="Caramel"/>
                <song name="Coffee &amp; TV"/>
                <song name="Tender"/>
                <song name="To the End"/>
                <song name="Country House"/>
                <song name="Parklife">
                    <with mbid="6c8e9f56-71bb-4b06-914f-f370da52e38c" name="Phil Daniels" sortName="Daniels, Phil">
                        <url>http://www.setlist.fm/setlists/phil-daniels-53d4bba1.html</url>
                    </with>
                </song>
                <song name="End of a Century"/>
                <song name="This Is a Low"/>
            </set>
            <set encore="1">
                <song name="Under the Westway"/>
                <song name="For Tomorrow"/>
                <song name="The Universal"/>
                <song name="Song 2">
                    <with mbid="6c8e9f56-71bb-4b06-914f-f370da52e38c" name="Phil Daniels" sortName="Daniels, Phil">
                        <url>http://www.setlist.fm/setlists/phil-daniels-53d4bba1.html</url>
                    </with>
                </song>
            </set>
        </sets>*/