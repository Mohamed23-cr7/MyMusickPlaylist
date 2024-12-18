package Music;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

class Song {
    public String title;
    public String artist;
    public float duration;

    public Song(String title, String artist, float duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void play() {
        System.out.println("Playing: " + title + " by " + artist + " [" + duration + " seconds]");
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + " sec)";
    }
}

class Playlist {
    public String name;
    public LinkedList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new LinkedList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
        System.out.println(song.getTitle() + " Added to Playlist.");
    }

    public void removeSong(String title) {
        boolean found = false;
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                songs.remove(song);
                System.out.println(title + " Removed From Playlist.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in Playlist.");
        }
    }

    public void rearrangeSong(int fromIndex, int toIndex) {
        if (fromIndex < songs.size() && toIndex < songs.size() && fromIndex != toIndex) {
            Song song = songs.remove(fromIndex);
            songs.add(toIndex, song);
            System.out.println("Song moved to new position.");
        } else {
            System.out.println("Invalid indices for rearranging.");
        }
    }


    public void playShuffled() {
        System.out.println("Playing playlist in shuffled order:");
        LinkedList<Song> shuffledSongs = new LinkedList<>(songs);
        Collections.shuffle(shuffledSongs, new Random());
        for (Song song : shuffledSongs) {
            song.play();
        }
    }

    public void displayPlaylist() {
        System.out.println("Playlist: " + name);
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }
}

public class MusicPlaylistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================================================");
        System.out.println("                   Welcome To Star Music App");
        System.out.println("=================================================================================");
        System.out.print("\nCreate Your Playlist (Playlist_Name): ");
        String playlistName = scanner.nextLine();
        Playlist playlist = new Playlist(playlistName);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Rearrange Song");
            System.out.println("4. Play Shuffled");
            System.out.println("5. Display Playlist");
            System.out.println("6. Play Songs");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter duration in seconds: ");
                    float duration = scanner.nextFloat();
                    scanner.nextLine();
                    Song newSong = new Song(title, artist, duration);
                    playlist.addSong(newSong);
                    break;

                case 2:
                    System.out.print("Enter the title of the song to remove: ");
                    String removeTitle = scanner.nextLine();
                    playlist.removeSong(removeTitle);
                    break;

                case 3:
                    System.out.print("Enter the index of the song to move from: ");
                    int fromIndex = scanner.nextInt();
                    System.out.print("Enter the index to move to: ");
                    int toIndex = scanner.nextInt();
                    playlist.rearrangeSong(fromIndex, toIndex);
                    break;

                case 4:
                    playlist.playShuffled();
                    break;


                case 5:
                    playlist.displayPlaylist();
                    break;

                case 6:
                    System.out.println("Select Your Song ");
                    playlist.displayPlaylist();
                    System.out.println(playlistName + "Playlist :) Playing Now" );
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
