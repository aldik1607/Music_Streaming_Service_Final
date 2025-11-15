package org.example.facade;

public class SongLoader
{
    public String load(String trackName)
    {
        System.out.println("SongLoader: Loading song..." + trackName);
        return trackName;
    }


}
