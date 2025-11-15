package org.example.menu;

import org.example.builder.TrackBuilder;
import org.example.models.Playlist;
import org.example.models.Track;
import org.example.observer.AnalyticsListener;
import org.example.observer.UiListener;
import org.example.strategy.*;
import org.example.decorator.*;
import org.example.facade.MusicFacade;
import org.example.facade.PlayerService;
import org.example.facade.PlaylistManager;
import org.example.facade.SongLoader;
import org.example.adapter.LyricsAdapter;
import org.example.adapter.LyricsProvider;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    private Player player;
    private ArrayList<Playlist> playlists;
    private Scanner scanner;
    private MusicFacade musicFacade;
    private LyricsProvider lyricsProvider;
    private boolean bass = false;
    private boolean echo = false;
    private boolean reverb = false;

    public ConsoleMenu() {
        scanner = new Scanner(System.in);
        playlists = new ArrayList<>();
        initializeData();

        player = new Player(playlists.get(0).getPlaylist(), new SequentialStrategy());
        player.registerObserver(new UiListener());
        player.registerObserver(new AnalyticsListener());
        lyricsProvider = new LyricsAdapter();
        musicFacade = new MusicFacade(
                new PlayerService(),
                new SongLoader(),
                new PlaylistManager(),
                lyricsProvider
        );
    }

    public void start() {
        System.out.println("Music Player Started\n");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    player.play();
                    break;
                case 2:
                    player.pause();
                    break;
                case 3:
                    player.next();
                    break;
                case 4:
                    changeStrategy();
                    break;
                case 5:
                    selectTrack();
                    break;
                case 6:
                    changePlaylist();
                    break;
                case 7:
                    showCurrentTrack();
                    break;
                case 8:
                    showAllPlaylists();
                    break;
                case 9:
                    createNewPlaylist();
                    break;
                case 10: playWithEffects();
                break;
                case 11: chooseEffects();
                break;
                case 12: showLyrics();
                break;
                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }

        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n--- Music Player Menu ---");
        System.out.println("1. Play");
        System.out.println("2. Pause");
        System.out.println("3. Next");
        System.out.println("4. Change Strategy");
        System.out.println("5. Select Track");
        System.out.println("6. Change Playlist");
        System.out.println("7. Show Current Track");
        System.out.println("8. Show All Playlists");
        System.out.println("9. Create New Playlist");
        System.out.println("10. Play with Effects (Decorator + Facade)");
        System.out.println("11. Choose Effects (Bass, Echo, Reverb)");
        System.out.println("12. Show Lyrics");
        System.out.println("0. Exit");
        System.out.println("-------------------------");
    }

    private void changeStrategy() {
        System.out.println("\nSelect Strategy:");
        System.out.println("1. Sequential");
        System.out.println("2. Repeat");
        System.out.println("3. Shuffle");

        int choice = getIntInput("Enter choice: ");

        switch (choice) {
            case 1:
                player.setStrategy(new SequentialStrategy());
                break;
            case 2:
                player.setStrategy(new RepeatStrategy());
                break;
            case 3:
                player.setStrategy(new ShuffleStrategy());
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void selectTrack() {
        Track current = player.getCurrent();
        if (current == null) {
            System.out.println("No tracks available");
            return;
        }

        int index = getIntInput("Enter track index: ");

        try {
            player.setCurrentIndex(index);
            System.out.println("Track selected");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    private void changePlaylist() {
        System.out.println("\nAvailable Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + ". " + playlists.get(i).getName());
        }

        int choice = getIntInput("Enter playlist number: ");

        if (choice >= 0 && choice < playlists.size()) {
            player.setPlaylist(playlists.get(choice).getPlaylist());
            System.out.println("Playlist changed");
        } else {
            System.out.println("Invalid playlist number");
        }
    }

    private void showCurrentTrack() {
        Track current = player.getCurrent();
        if (current != null) {
            System.out.println("Current: " + current);
            System.out.println("Index: " + player.getCurrentIndex());
        } else {
            System.out.println("No track selected");
        }
    }

    private void showAllPlaylists() {
        System.out.println("\nAll Playlists:");
        for (Playlist playlist : playlists) {
            System.out.println("\n" + playlist.getName() + ":");
            for (int i = 0; i < playlist.getPlaylist().size(); i++) {
                System.out.println("  " + i + ". " + playlist.getPlaylist().get(i).getTitle());
            }
        }
    }
    private void chooseEffects(){
        System.out.println("\nChoose Effects:");
        System.out.println("1. BassBoost (" + (bass ? "ON" : "OFF") + ")");
        System.out.println("2. Echo (" + (echo ? "ON" : "OFF") + ")");
        System.out.println("3. Reverb (" + (reverb ? "ON" : "OFF") + ")");
        System.out.println("0. Back");
        int c = getIntInput("Enter: ");
        switch (c)  {
            case 1 -> bass = !bass;
            case 2 -> echo = !echo;
            case 3 -> reverb = !reverb;
            case 0 -> {return; }
            default -> System.out.println("Invalid choice");
        }
    }


    private  AudioStream buildEffects (String trackName){
        AudioStream stream =  new BaseStream(trackName);
        if (bass) stream = new BassBoost(stream);
        if (echo) stream = new Echo(stream);
        if (reverb) stream = new Reverb(stream);

        return stream;
    }
    private void playWithEffects(){
        Track current = player.getCurrent();
        if (current == null) {
            System.out.println("No track selected");
            return;
        }
        String trackName = current.getArtist() + " - " + current.getTitle();
        AudioStream stream = buildEffects(trackName);
        musicFacade.playTrack(stream, trackName);
    }
    private void showLyrics(){
        Track current = player.getCurrent();
        if (current == null) {
            System.out.println("No track selected");
            return;

        }
        String trackName = current.getArtist() + " - " + current.getTitle();
        System.out.println("\n=== LYRICS ===");
        System.out.println(lyricsProvider.getLyrics(trackName));
    }
    private void createNewPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();

        ArrayList<Track> tracks = new ArrayList<>();
        TrackBuilder builder = new TrackBuilder();

        System.out.println("Add tracks (enter 0 to finish):");

        int trackNum = 1;
        while (true) {
            System.out.println("\nTrack " + trackNum + ":");
            System.out.print("Enter 0 to finish, or 1 to add track: ");
            int choice = getIntInput("");

            if (choice == 0) break;

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Artist: ");
            String artist = scanner.nextLine();

            System.out.print("Album: ");
            String album = scanner.nextLine();

            int duration = getIntInput("Duration (seconds): ");

            String id = "custom_" + System.currentTimeMillis();
            tracks.add(createTrack(builder, id, title, artist, duration, album));

            System.out.println("Track added");
            trackNum++;
        }

        if (!tracks.isEmpty()) {
            Playlist newPlaylist = new Playlist(name, tracks);
            playlists.add(newPlaylist);
            System.out.println("Playlist created: " + name);
        } else {
            System.out.println("No tracks added, playlist not created");
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private void initializeData() {
        TrackBuilder builder = new TrackBuilder();

        ArrayList<Track> rockTracks = new ArrayList<>();
        rockTracks.add(createTrack(builder, "001", "Bohemian Rhapsody", "Queen", 355, "A Night at the Opera"));
        rockTracks.add(createTrack(builder, "002", "Stairway to Heaven", "Led Zeppelin", 482, "Led Zeppelin IV"));
        rockTracks.add(createTrack(builder, "003", "Hotel California", "Eagles", 391, "Hotel California"));
        playlists.add(new Playlist("Rock Classics", rockTracks));

        ArrayList<Track> popTracks = new ArrayList<>();
        popTracks.add(createTrack(builder, "101", "Billie Jean", "Michael Jackson", 294, "Thriller"));
        popTracks.add(createTrack(builder, "102", "Like a Prayer", "Madonna", 340, "Like a Prayer"));
        playlists.add(new Playlist("Pop Hits", popTracks));
    }

    private Track createTrack(TrackBuilder builder, String id, String title, String artist, int duration, String album) {
        builder.createNewTrack();
        builder.buildId(id);
        builder.buildTitle(title);
        builder.buildArtist(artist);
        builder.buildDuration(duration);
        builder.buildAlbum(album);
        return builder.getTrack();
    }
}