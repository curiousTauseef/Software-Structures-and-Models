package com.example.wontheone.lab03_04_orientation_lifecycle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pghoo on 2016-03-16.
 */
public class CreatureCollection implements Serializable{

    // http://docs.oracle.com/javase/7/docs/platform/serialization/spec/version.html#6678
    private static final long serialVersionUID = 8184507323616640712L;
    List<Creature> creatureList;
    int numOfElement;

    public CreatureCollection() {
        creatureList = new ArrayList<>();
    }

    public void add(Creature creature){
        creatureList.add(creature);
        numOfElement++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Creature c : creatureList) {
            sb.append(c);
        }
        return sb.toString();
    }

    public int getNumOfElement() {
        return numOfElement;
    }

    public List<Creature> getCreatureList() {
        return creatureList;
    }
}
