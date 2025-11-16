package org.example.facade;
import org.example.decorator.*;

public class PlayerService {
    public void play(AudioStream stream) {
        System.out.println("PlayerService: Starting playback...");
        System.out.println("--- EFFECTS APPLIED ---");
        stream.play();
        System.out.println("--- PLAYBACK END ---");
    }
    public void stop(){
        System.out.println("PlayerService: Stopping playback...");
    }
}
