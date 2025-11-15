package org.example.adapter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ExternalLyricsService {
    public String fetchLyricText(String song,String artist) {
        String fileName = artist.replace(" ", "") + "-" + song.replace(" ", "") + ".txt" ;
        String path = "lyrics/" + fileName;
        try {

            InputStream in = getClass().getClassLoader()
                    .getResourceAsStream(path);
            if (in == null) {
                return "[External API] Lyrics not found: " + artist + " - " + song; // FIX
            }


            return new String(in.readAllBytes(), StandardCharsets.UTF_8); // FIX
        } catch (Exception e) {
            return "[External API ERROR] Cannot read: " + fileName;
        }
    }
}
