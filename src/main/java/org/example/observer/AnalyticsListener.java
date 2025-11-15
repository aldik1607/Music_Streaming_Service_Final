package org.example.observer;

import org.example.models.Track;

public class AnalyticsListener implements PlayerObserver {

    @Override
    public void onPlay(Track track) {
        System.out.println("[Analytics] Play event: trackId=" + track.getId());
    }

    @Override
    public void onPause(Track track) {
        System.out.println("[Analytics] Pause event: trackId=" + track.getId());
    }

    @Override
    public void onTrackChanged(Track oldTrack, Track newTrack) {
        String from = oldTrack != null ? oldTrack.getTitle() : "none";
        String to = newTrack != null ? newTrack.getTitle() : "none";
        System.out.println("[Analytics] Track changed from " + from + " to " + to);
    }
}
