package com.bono.api;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by bono on 12/18/16.
 */
public class StoredPlaylists extends AbstractController {

    public static final String LISTPLAYLIST = "listplaylist";

    public static final String LISTPLAYLISTINFO = "listplaylistinfo";

    public static final String LISTPLAYLISTS = "listplaylists";

    public static final String LOAD = "load";

    public static final String PLAYLISTADD = "playlistadd";

    public static final String PLAYLISTCLEAR = "playlistclear";

    public static final String PLAYLISTDELETE = "playlistdelete";

    public static final String PLAYLISTMOVE = "playlistmove";

    public static final String RENAME = "rename";

    public static final String RM = "rm";

    public static final String SAVE = "save";


    public StoredPlaylists(ClientExecutor clientExecutor) {
        super(clientExecutor);
    }

    /*
    Lists the songs in the playlist.
     */
    public SongList listplaylist(String name) throws IOException {
        return new SongList(clientExecutor.execute(LISTPLAYLIST, name));
    }

    /*
    Print a list of the playlist directory.
     */
    public Collection<String> listplaylists() throws IOException {
        return clientExecutor.execute(LISTPLAYLISTS);
    }
}
