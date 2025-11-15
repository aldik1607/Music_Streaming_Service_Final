package org.example.builder;

public class TrackBuilder extends Builder {
    @Override
    public void buildId(String id) {
        track.setId(id);
    }

    @Override
    public void buildTitle(String title) {
        track.setTitle(title);
    }

    @Override
    public void buildArtist(String artist) {
        track.setArtist(artist);
    }

    @Override
    public void buildDuration(int durationSeconds) {
        track.setDurationSeconds(durationSeconds);
    }

    @Override
    public void buildAlbum(String album) {
        track.setAlbum(album);
    }


}