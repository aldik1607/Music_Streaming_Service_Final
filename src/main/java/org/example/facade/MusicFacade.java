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
    public void playTrack(AudioStream stream, String trackName) {
        String loaded = songLoader.load(trackName);
        System.out.println("\n=== PLAYBACK START ===");
        playerService.play(stream);
        String original = lyricsProvider.getLyrics(loaded);
        if (original != null) {
            String processed = stream.processLyrics(original);
            System.out.println("\n=== LYRICS (WITH EFFECTS) ===");
            System.out.println(processed);
        } else {
            System.out.println("\n(No lyrics available)");
        }

        System.out.println("\n=== FACADE END ===");
    }

    public void showPlaylist(java.util.List<String> tracks) {
        playlistManager.printPlaylist(tracks);
    }

    public void stopTrack() {
        playerService.stop();
    }
}