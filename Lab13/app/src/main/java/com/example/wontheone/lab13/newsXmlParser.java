package com.example.wontheone.lab13;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-23.
 */
public class newsXmlParser {

    protected static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    // processing the feed
    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List items = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the tag or player tag
            if (name.equals("channel")){
                items = readChannel(parser);
            } else {
                skip(parser);
            }
        }
        return items;
    }

    private List readChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        List items = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the tag or player tag
            if (name.equals("item")){
                items.add(readItem(parser));
            } else {
                skip(parser);
            }
        }
        return items;
    }

    // Parses the contents of an player. If it encounters a id, name tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private Item readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        int id = 0;
        String title, link, description, pubDate;
        title = link = description = pubDate = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if (tagName.equals("title")) {
                title = readTagText(parser, "title");
            } else if (tagName.equals("link")) {
                link = readTagText(parser, "link");
            } else if (tagName.equals("description")) {
                description = readTagText(parser, "description");
            } else if (tagName.equals("pubDate")) {
                pubDate = readTagText(parser, "pubDate");
            }  else {
                skip(parser);
            }
        }
        return new Item(title, link, description, pubDate);
    }

    // Processes name tags in the feed.
    private String readTagText(XmlPullParser parser, String tagName) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tagName);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tagName);
        return title;
    }

    // For the name tag, extracts their text value.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
