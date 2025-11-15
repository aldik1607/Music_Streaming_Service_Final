package org.example.facade;

public class SongLoader
{
    public String load(String trackName)
    {
        System.out.println("SongLoader: Loading track => " + trackName);
        return trackName;
    }


}
