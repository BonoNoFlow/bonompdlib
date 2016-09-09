package com.bono.api;

import com.bono.api.protocol.Tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bono on 9/9/16.
 */
public class Database extends AbstractController {

    public static final String LIST   = "list";

    public static final String LSINFO = "lsinfo";

    public Database(ClientExecutor clientExecutor) {
        super(clientExecutor);
    }

    public Collection<String> list(String... args) throws IOException {
        return clientExecutor.execute(LIST, args);
    }

    public Collection<String> lsinfo(String uri) throws IOException {
        return clientExecutor.execute(LSINFO, uri);
    }


    public List<Artist> queryArtist() throws IOException {
        // query artist
        Collection<String> artistCollection = list(Tags.ARTIST);

        // convert collection<String> to list<Artist>.
        List<Artist> artists = null;
        if (artistCollection != null) {
            artists = new ArrayList<>(artistCollection.size());
            for (String s : artistCollection) {
                artists.add(new Artist(s));
            }
        }
        return artists;
    }

    public List<File> queryFiles(String uri) throws IOException {
        // query files
        Collection<String> filesCollection = lsinfo(uri);

        // convert collection<String> to list<File>.
        List<File> files = null;
        if (filesCollection != null) {
            files = new ArrayList<>(filesCollection.size());
            for (String s : filesCollection) {
                files.add(new File(s));
            }
        }
        return files;
    }

    public SongList search(String... args) throws IOException {
        return null;
    }

    public void update(String uri) throws IOException {

    }
}
