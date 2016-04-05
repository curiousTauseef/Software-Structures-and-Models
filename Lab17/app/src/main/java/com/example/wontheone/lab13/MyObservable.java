package com.example.wontheone.lab13;

import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-03.
 */
public interface MyObservable {
    public void register(MyObserver observer);
    public void notifyObservers(List<XmlParser.Player> players);
}
