import com.bono.api.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by hendriknieuwenhuis on 23/03/16.
 */
public class PlaylistTest extends Test {

    Playlist playlist;

    DefaultListModel<Song> songs = new DefaultListModel<>();

    public PlaylistTest(Config config) {
        super(config);
        playlist = new Playlist(dbExecutor);
        playlist.addSongListener(new SongListener());
        playlist.addListener(new PlaylistListener());

        test();
    }

    @Override
    public void test() {
        String reply = "";
        try {
            reply = playlist.playlistinfo(null);
        } catch (Exception e){
            e.printStackTrace();
        }
        playlist.populate(reply);

        reply = "";
        try {
            reply = playlist.playlistinfo(null);
        } catch (Exception e){
            e.printStackTrace();
        }
        playlist.populate(reply);

        reply = "";
        try {
            reply = playlist.playlistinfo(null);
        } catch (Exception e){
            e.printStackTrace();
        }
        playlist.populate(reply);

        reply = "";
        try {
            reply = playlist.playlistinfo(null);
        } catch (Exception e){
            e.printStackTrace();
        }
        playlist.populate(reply);
    }

    public static void main(String[] args) {

        new PlaylistTest(new Config("192.168.2.4", 6600));



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
