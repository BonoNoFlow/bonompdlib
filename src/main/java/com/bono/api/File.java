package com.bono.api;

/**
 * Created by bono on 9/9/16.
 */
public class File implements Comparable<File> {

    protected String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(File o) {
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
}
