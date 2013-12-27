package com.ntalfer.getthesetlist;

/**
 * Created by nicolas on 25/12/13.
 */
public class Artist {

    private String name;
    private String mbid;

    private final static String Blur_mbid = "ba853904-ae25-4ebb-89d6-c44cfbd71bd2";
    private final static String Blur_tour = "2013%2F2014%20Festival%20Tour";

    public Artist(String name, String mbid) {
        this.name = name;
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMbid() {
        return mbid;
    }
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public static Artist getBlur()
    {
        return new Artist("Blur", Blur_mbid);
    }

    public static String getBlurTourUrl()
    {
        StringBuffer url = new StringBuffer("http://api.setlist.fm/rest/0.1/artist/").append(Blur_mbid).append("/tour/").append(Blur_tour);
        return url.toString();
    }
}
