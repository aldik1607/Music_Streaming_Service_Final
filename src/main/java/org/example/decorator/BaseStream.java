package org.example.decorator;

public class BaseStream implements AudioStream
{
    private String trackName;
    public BaseStream(String trackName){
        this.trackName = trackName;
    }



    @Override
    public void play()
    {
        System.out.println("Playing track: " + trackName);
    }
    @Override
    public String processLyrics(String lyrics) {
        return lyrics;
    }
}
