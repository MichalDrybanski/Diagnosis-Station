package org.example.model;

public class Id {
    private static Integer id = 0;

    public static Integer get() {
        id++;
        return id;
    }
}
