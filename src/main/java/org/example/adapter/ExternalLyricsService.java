package org.example.adapter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalLyricsService {
    public String fetchLyricText(String song,String artist) {
        String fileName = artist.replace(" ", "") + "-" + song.replace(" ", "") + ".txt" ;
        try {
            Path path = Path.of( "src/main/resources/lyrics/" + fileName );
            return Files.readString(path);
        } catch(Exception e){
            return "[External API] Lyrics not found: " + artist + " - " + song ;
        }
    }
}
