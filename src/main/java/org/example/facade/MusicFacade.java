package org.example.facade;
import org.example.adapter.LyricsProvider;
import org.example.decorator.AudioStream;

import java.util.List;
public class MusicFacade {
    private final PlayerService playerService;
    private final SongLoader songLoader;
    private final PlaylistManager playlistManager;
    private final LyricsProvider lyricsProvider;

    public MusicFacade(PlayerService playerService, SongLoader songLoader, PlaylistManager playlistManager, LyricsProvider lyricsProvider) {
        this.playerService = playerService;
        this.songLoader = songLoader;
        this.playlistManager = playlistManager;
        this.lyricsProvider = lyricsProvider;
    }
    public void playTrack(AudioStream stream, String trackName)
    {
        String loaded = songLoader.load(trackName);
        playerService.play(stream);
        System.out.println("\n=== LYRICS ===");
        System.out.println(lyricsProvider.getLyrics(loaded));
        System.out.println("\n=== MUSIC FACADE END ===");

    }
    public void showPlaylist(List<String> tracks)
    {
        playlistManager.printPlaylist(tracks);
    }
    public void stopTrack()
    {
        playerService.stop();
    }
}
