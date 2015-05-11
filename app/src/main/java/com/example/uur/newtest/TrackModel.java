package com.example.uur.newtest;

/**
 * Created by Uğur on 17.2.2015.
 */
public class TrackModel {
    public String voteid;
    public String priority;
    public String trackid;
    public String artistimage;
    public String artistname;
    public String trackname;
    public String albumname;

    public TrackModel() {

    }

    public TrackModel(String voteid, String priority, String trackid, String artistimage, String artistname, String trackname, String albumname) {
        this.voteid = voteid;
        this.priority = priority;
        this.trackid = trackid;
        this.artistimage = artistimage;
        this.artistname = artistname;
        this.trackname = trackname;
        this.albumname = albumname;
    }

    public String getVoteid() {
        return voteid;
    }

    public void setVoteid(String voteid) {
        this.voteid = voteid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getArtistimage() {
        return artistimage;
    }

    public void setArtistimage(String artistimage) {
        this.artistimage = artistimage;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    @Override
    public String toString() {
        return "TrackModel{" +
                "voteid='" + voteid + '\'' +
                ", priority='" + priority + '\'' +
                ", trackid='" + trackid + '\'' +
                ", artistimage='" + artistimage + '\'' +
                ", artistname='" + artistname + '\'' +
                ", trackname='" + trackname + '\'' +
                ", albumname='" + albumname + '\'' +
                '}';
    }
}
