package com.example.wontheone.lab13;

import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-03.
 */
public interface MyObserver {
    public void updateUi(List<XmlParser.Player> players);
}
