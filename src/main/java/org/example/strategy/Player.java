package org.example.strategy;

import org.example.models.Track;
import org.example.observer.PlayerObserver;
import org.example.observer.PlayerSubject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements PlayerSubject {
    private List<Track> playlist;
    private PlayBackStrategy strategy;
    private int currentIndex = 0;
    private final CopyOnWriteArrayList<PlayerObserver> observers = new CopyOnWriteArrayList<>();


    public Player(List<Track> playlist, PlayBackStrategy strategy) {
        this.playlist = playlist;
        this.strategy = strategy;
    }

    @Override
    public void registerObserver(PlayerObserver observer) {
        if (observer != null) observers.addIfAbsent(observer);
    }

    @Override
    public void unregisterObserver(PlayerObserver observer) {
        observers.remove(observer);
    }


    private void notifyPlay(Track track) {
        for (PlayerObserver o : observers) o.onPlay(track);
    }

    private void notifyPause(Track track) {
        for (PlayerObserver o : observers) o.onPause(track);
    }

    private void notifyTrackChanged(Track oldTrack, Track newTrack) {
        for (PlayerObserver o : observers) o.onTrackChanged(oldTrack, newTrack);
    }

    public void setStrategy(PlayBackStrategy strategy) {
        this.strategy = strategy;
        System.out.println("Strategy changed to: " + strategy.getClass().getSimpleName());
    }

    public void play() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (currentIndex < 0 || currentIndex >= playlist.size()) {
            System.out.println("End of playlist.");
            return;
        }

        Track track = playlist.get(currentIndex);
        System.out.println("Playing: " + track);

        notifyPlay(track);
    }

    public void next() {
        if (playlist == null || playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        Track oldTrack = getCurrent();

        if (currentIndex == -1) {
            System.out.println("Playback already finished.");
            return;
        }

        currentIndex = strategy.next(playlist, currentIndex);

        if (currentIndex == -1) {
            System.out.println("Playback finished.");
            notifyTrackChanged(oldTrack, null);
        } else {
            Track newTrack = playlist.get(currentIndex);
            notifyTrackChanged(oldTrack, newTrack);
            play();
        }
    }

    public void setPlaylist(List<Track> playlist) {
        Track oldTrack = (this.playlist != null && !this.playlist.isEmpty() && currentIndex < this.playlist.size())
                ? this.playlist.get(currentIndex) : null;
        this.playlist = playlist;
        this.currentIndex = 0;
        Track newTrack = playlist.isEmpty() ? null : playlist.get(0);
        notifyTrackChanged(oldTrack, newTrack);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int index) {
        if (index >= 0 && index < playlist.size()) {
            Track oldTrack = playlist.get(currentIndex);
            this.currentIndex = index;
            Track newTrack = playlist.get(currentIndex);
            notifyTrackChanged(oldTrack, newTrack);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public Track getCurrent() {
        if (playlist == null || playlist.isEmpty() || currentIndex < 0 || currentIndex >= playlist.size()) return null;
        return playlist.get(currentIndex);
    }

    public void pause() {
        Track track = getCurrent();
        if (track != null) {
            System.out.println("Paused: " + track);
            notifyPause(track);
        }
    }
}