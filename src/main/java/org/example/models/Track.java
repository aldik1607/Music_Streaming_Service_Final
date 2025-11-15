package org.example.models;

public class Track  {
    private String id;
    private String title;
    private String artist;
    private String album;
    private int durationSec;


    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public int getDurationSec() { return durationSec; }

    @Override
    public String toString() {
        return String.format("[%s] %s — %s (%ds) album=%s genre=%s source=%s",
                id, title, artist, durationSec,
                album == null ? "-" : album);
    }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setDurationSeconds(int durationSeconds) { this.durationSec = durationSeconds; }
    public void setAlbum(String album) { this.album = album; }


}