package org.example.strategy;

import org.example.models.Track;

import java.util.List;

public class RepeatStrategy implements PlayBackStrategy {
    @Override
    public int next(List<Track> playlist, int currentIndex) {
        if (playlist == null || playlist.isEmpty()) {
            return -1;
        }
        return (currentIndex + 1) % playlist.size();
    }
}