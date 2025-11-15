package org.example.observer;

import org.example.models.Track;

public class UiListener implements PlayerObserver {

    @Override
    public void onPlay(Track track) {
        System.out.println("[UI] Now playing: " + track.getTitle());
    }

    @Override
    public void onPause(Track track) {
        System.out.println("[UI] Paused: " + track.getTitle());
    }

    @Override
    public void onTrackChanged(Track oldTrack, Track newTrack) {
        System.out.println("[UI] Track changed to: " + (newTrack != null ? newTrack.getTitle() : "none"));
    }
}
