package com.bono.api;

import java.util.Comparator;

/**
 * Created by Hendrik Nieuwenhuis 8/29/16.
 */
public class Artist implements Comparable<Artist> {

    public static Comparator<Artist> artistComparator = new Comparator<Artist>() {
        @Override
        public int compare(Artist o1, Artist o2) {
            return o1.compareTo(o2);
        }
    };

    protected String name;

    public Artist(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Artist o) {
        int comp = 0;

        comp = name.compareToIgnoreCase(o.getName());

        // in case of same name, compare with casing.
        if (comp == 0) {
            comp = name.compareTo(o.getName());
        }

        return comp;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        return name.equals(artist.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
