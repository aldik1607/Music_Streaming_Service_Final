package org.example.strategy;

import org.example.models.Track;

import java.util.List;

public class SequentialStrategy implements PlayBackStrategy {
    @Override
    public int next(List<Track> playlist, int currentIndex) {
        if (playlist == null || playlist.isEmpty()) {
            return -1;
        }
        int nextIndex = currentIndex + 1;
        return nextIndex < playlist.size() ? nextIndex : -1;
    }
}