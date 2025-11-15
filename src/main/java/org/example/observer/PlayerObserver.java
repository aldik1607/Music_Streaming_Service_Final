package org.example.observer;

import org.example.models.Track;

public interface PlayerObserver {
    void onPlay(Track track);
    void onPause(Track track);
    void onTrackChanged(Track oldTrack, Track newTrack);
}
