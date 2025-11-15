package org.example.observer;

public interface PlayerSubject {
    void registerObserver(PlayerObserver observer);
    void unregisterObserver(PlayerObserver observer);
}