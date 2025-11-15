package org.example.strategy;

import org.example.models.Track;

import java.util.List;
import java.util.Random;

public class ShuffleStrategy implements PlayBackStrategy {
    private final Random random = new Random();

    @Override
    public int next(List<Track> playlist, int currentIndex) {
        if (playlist == null || playlist.isEmpty()) {
            return -1;
        }

        if (playlist.size() == 1) {
            return 0;
        }

        int nextIndex;
        do {
            nextIndex = random.nextInt(playlist.size());
        } while (nextIndex == currentIndex);

        return nextIndex;
    }
}