package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 14/04/16.
 */
public class Database extends Exec {

    /*
    count {TAG} {NEEDLE} [...] [group] [GROUPTYPE]
    Counts the number of songs and their total playtime in the db matching TAG exactly.
    The group keyword may be used to group the results by a tag. The following prints per-artist counts:
    count group artist*/
    public static final String COUNT = "count";

    public String count(String... args) throws Exception {
        return execCommand(COUNT, args);
    }
    /*
    find {TYPE} {WHAT} [...] [window START:END]
    Finds songs in the db that are exactly WHAT. TYPE can be any tag supported by MPD, or one of the special parameters:
    .any checks all tag values
    .file checks the full path (relative to the music directory)
    .base restricts the search to songs in the given directory (also relative to the music directory)
    .modified-since compares the file's time stamp with the given value (ISO 8601 or UNIX time stamp)
    WHAT is what to find.
    window can be used to query only a portion of the real response.
    The parameter is two zero-based record numbers; a start number and an end number.*/
    public static final String FIND = "find";

    public String findd(String... args) throws Exception {
        return execCommand(FIND, args);
    }

    /*
    findadd {TYPE} {WHAT} [...]
    Finds songs in the db that are exactly WHAT and adds them to current
    playlist. Parameters have the same meaning as for find.*/
    public static final String FINDADD = "findadd";

    public String findadd(String... args) throws Exception {
        return execCommand(FINDADD, args);
    }

    /*
    list {TYPE} [FILTERTYPE] [FILTERWHAT] [...] [group] [GROUPTYPE] [...]
    Lists unique tags values of the specified type. TYPE can be any tag supported by MPD or file.
    Additional arguments may specify a filter like the one in the find command.
    The group keyword may be used (repeatedly) to group the results by one or more tags. The following
    example lists all album names, grouped by their respective (album) artist:
    list album group albumartist*/
    public static final String LIST = "list";

    public String list(String... args) throws Exception {
        return execCommand(LIST, args);
    }

    /*
    listall [URI]
    Lists all songs and directories in URI.
    Do not use this command. Do not manage a client-side copy of MPD's database.
    That is fragile and adds huge overhead. It will break with large databases.
    Instead, query MPD whenever you need something.*/
    public static final String LISTALL = "listall";

    public String listall(String... args) throws Exception {
        return execCommand(LISTALL, args);
    }

    /*
    listallinfo [URI]
    Same as listall, except it also returns metadata info in the same format as lsinfo.
    Do not use this command. Do not manage a client-side copy of MPD's database. That is fragile and
    adds huge overhead. It will break with large databases. Instead, query MPD whenever you need something.*/
    public static final String LISTALLINFO = "listallinfo";

    public String listallinfo(String... args) throws Exception {
        return execCommand(LISTALLINFO, args);
    }

    /*
    listfiles [URI]
    Lists the contents of the directory URI, including files are not recognized by MPD. URI can be a
    path relative to the music directory or an URI understood by one of the storage plugins. The response
    contains at least one line for each directory entry with the prefix "file: " or "directory: ",
    and may be followed by file attributes such as "Last-Modified" and "size".
    For example, "smb://SERVER" returns a list of all shares on the given SMB/CIFS server;
    "nfs://servername/path" obtains a directory listing from the NFS server.*/
    public static final String LISTFILES = "listfiles";

    public String listfiles(String... args) throws Exception {
        return execCommand(LISTFILES, args);
    }


    /*

lsinfo [URI]

Lists the contents of the directory URI.

When listing the root directory, this currently returns the list of stored playlists. This behavior is deprecated; use "listplaylists" instead.

This command may be used to list metadata of remote files (e.g. URI beginning with "http://" or "smb://").

Clients that are connected via UNIX domain socket may use this command to read the tags of an arbitrary local file (URI is an absolute path).

readcomments [URI]

Read "comments" (i.e. key-value pairs) from the file specified by "URI". This "URI" can be a path relative to the music directory or an absolute path.

This command may be used to list metadata of remote files (e.g. URI beginning with "http://" or "smb://").

The response consists of lines in the form "KEY: VALUE". Comments with suspicious characters (e.g. newlines) are ignored silently.

The meaning of these depends on the codec, and not all decoder plugins support it. For example, on Ogg files, this lists the Vorbis comments.

search {TYPE} {WHAT} [...] [window START:END]

Searches for any song that contains WHAT. Parameters have the same meaning as for find, except that search is not case sensitive.

searchadd {TYPE} {WHAT} [...]

Searches for any song that contains WHAT in tag TYPE and adds them to current playlist.

Parameters have the same meaning as for find, except that search is not case sensitive.

searchaddpl {NAME} {TYPE} {WHAT} [...]

Searches for any song that contains WHAT in tag TYPE and adds them to the playlist named NAME.

If a playlist by that name doesn't exist it is created.

Parameters have the same meaning as for find, except that search is not case sensitive.

update [URI]

Updates the music database: find new files, remove deleted files, update modified files.

URI is a particular directory or song/file to update. If you do not specify it, everything is updated.

Prints "updating_db: JOBID" where JOBID is a positive number identifying the update job. You can read the current job id in the status response.

rescan [URI]

Same as update, but also rescans unmodified files.

     */

    public Database(DBExecutor dbExecutor) {
        super(dbExecutor);
    }
}
