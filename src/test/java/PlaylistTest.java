import com.bono.api.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by hendriknieuwenhuis on 23/03/16.
 */
public class PlaylistTest {

    static PlaylistTest playlistTest;

    Playlist playlist = new Playlist();

    DefaultListModel<Song> songs = new DefaultListModel<>();

    public PlaylistTest(String entry) {

        playlist.addSongListener(new SongListener());
        playlist.addListener(new PlaylistListener());
        playlist.populate(entry);
    }

    public static void main(String[] args) {
        MPDEndpoint endpoint = new MPDEndpoint("192.168.2.4", 6600);

        String reply = "";
        try {
            reply = endpoint.command(new DefaultCommand(PlaylistProperties.PLAYLISTINFO));
        } catch (Exception e){
            e.printStackTrace();
        }
        playlistTest = new PlaylistTest(reply);

        reply = "";
        try {
            reply = endpoint.command(new DefaultCommand(PlaylistProperties.PLAYLISTINFO));
        } catch (Exception e){
            e.printStackTrace();
        }
        playlistTest.playlist.populate(reply);

        reply = "";
        try {
            reply = endpoint.command(new DefaultCommand(PlaylistProperties.PLAYLISTINFO));
        } catch (Exception e){
            e.printStackTrace();
        }
        playlistTest.playlist.populate(reply);

        reply = "";
        try {
            reply = endpoint.command(new DefaultCommand(PlaylistProperties.PLAYLISTINFO));
        } catch (Exception e){
            e.printStackTrace();
        }
        playlistTest.playlist.populate(reply);
    }

    /* Song listener adds each added song to the songs list model. */
    private class SongListener implements ChangeListener {


        @Override
        public void stateChanged(ChangeEvent e) {
            Song song = (Song) e.getSource();

            if (song.getPos().equals("0")) {
                System.out.println(getClass().getName() + " new list write");
                songs.clear();
            }
            songs.add(Integer.parseInt(song.getPos()), song);
        }
    }

    /* Playlist listener triggers change state at end of playlist writing. */
    private  class PlaylistListener implements ChangeListener {


        @Override
        public void stateChanged(ChangeEvent e) {
            System.out.println(getClass().getName() + " Playlist written.\n");
        }
    }
}
