package com.example.wontheone.lab13;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-03.
 */
public class Model implements MyObservable {

    public MyObserver myObserver;
    private static Model modelInstance = new Model();
    final ArrayList<XmlParser.Player> players = new ArrayList<XmlParser.Player>();

    public static Model getInstance() {
        return modelInstance;
    }

    private Model(){

    }

    public ArrayList<XmlParser.Player> getPlayers() {
        return players;
    }

    public void addPlayers(List<XmlParser.Player> newPlayers){
        this.players.addAll(newPlayers);
        notifyObservers(players);
    }

    @Override
    public void register(MyObserver observer) {
        myObserver = observer;
    }

    @Override
    public void notifyObservers(List<XmlParser.Player> players) {
        myObserver.updateUi(players);
    }
}
