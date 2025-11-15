package org.example.adapter;

public class LyricsAdapter implements LyricsProvider {
    private ExternalLyricsService externalLyricsService = new ExternalLyricsService();

    @Override
    public String getLyrics(String trackName){
        if (trackName == null || !trackName.contains(" - ")){
            return "[Adapter] Wrong track format. Expected: Artist - Song";}
    String[] parts = trackName.split(" - ");
        if (parts.length < 2){
        return "[Adapter] Cannot parse trackName";
         }
    String artist = parts[0].trim();
    String song = parts[1].trim();


        return externalLyricsService.fetchLyricText(song, artist);
    }
}
