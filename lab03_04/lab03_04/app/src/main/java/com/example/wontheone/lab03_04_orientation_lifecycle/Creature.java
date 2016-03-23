package com.example.wontheone.lab03_04_orientation_lifecycle;

import java.io.Serializable;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-16.
 */
public class Creature implements Serializable {

    // http://docs.oracle.com/javase/7/docs/platform/serialization/spec/version.html#6678
    private static final long serialVersionUID = 2026856921539527464L;
    private CreatureType type;
    private String name;

    public Creature(CreatureType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
