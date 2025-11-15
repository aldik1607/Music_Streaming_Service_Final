package org.example.facade;
import java.util.List;
public class PlaylistManager
{
     public void printPlaylist(List<String> tracks)
        {
            System.out.println("playlist:");
            for(int i = 0; i < tracks.size(); i++ ){
                System.out.println(i + ".  " + tracks.get(i));}
        }
}
