package org.example.facade;
import org.example.decorator.*;

public class PlayerService {
    public void play(AudioStream stream) {
        System.out.println("Playing");
        stream.play();
    }
    public void stop(){
        System.out.println("PlayerService: Stopping playback...");
    }
}
