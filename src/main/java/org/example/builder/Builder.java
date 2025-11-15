package org.example.builder;

import org.example.models.Track;

public abstract class Builder {
    protected Track track;

    public void createNewTrack() {
        track = new Track();
    }

    public Track getTrack() {
        return track;
    }

    public abstract void buildId(String id);
    public abstract void buildTitle(String title);
    public abstract void buildArtist(String artist);
    public abstract void buildDuration(int durationSeconds);
    public abstract void buildAlbum(String albumName);
}
