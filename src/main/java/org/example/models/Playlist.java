package org.example.models;

import java.util.ArrayList;

public class Playlist {
    private final String name;
    private final ArrayList<Track> playlist;

    public Playlist(String name, ArrayList<Track> playlist) {
        this.name = name;
        this.playlist = playlist;
    }

    public void addTrack(Track track) {
        playlist.add(track);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Track> getPlaylist() {
        return playlist;
    }


    @Override
    public String toString() {
        return String.format("Playlist: %s, Tracks: %d", name, playlist.size());
    }


}