package com.example.wontheone.lab13;

import java.util.Date;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-23.
 */
public class Item {

    public Item(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    String title, link, description, pubDate;
}
