package org.example.strategy;

import org.example.models.Track;

import java.util.List;

public interface PlayBackStrategy {
    int next(List<Track> playList, int currentIndex);
}