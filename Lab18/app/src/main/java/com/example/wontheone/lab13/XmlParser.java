package com.example.wontheone.lab13;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-23.
 */
public class XmlParser {

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
        List players = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "players");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the tag or player tag
            if (name.equals("player")){
                players.add(readPlayer(parser));
            } else {
                skip(parser);
            }
        }
        return players;
    }

    public static class Player implements Serializable {
        public final int id;
        public final String name;
        public Player(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return getId()+":"+getName();
        }
    }

    // Parses the contents of an player. If it encounters a id, name tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private Player readPlayer(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "player");
        int id = 0;
        String name = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if (tagName.equals("id")) {
                id = readId(parser);
            } else if (tagName.equals("name")) {
                name = readName(parser);
            } else {
                skip(parser);
            }
        }
        return new Player(id, name);
    }

    // Processes id tags in the feed.
    private int readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "id");
        int Id = readInt(parser);
        parser.require(XmlPullParser.END_TAG, ns, "id");
        return Id;
    }

    // Processes name tags in the feed.
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
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

    // For the Id tag, extracts their text value as int.
    private int readInt(XmlPullParser parser) throws IOException, XmlPullParserException {
        int result = 0;
        if (parser.next() == XmlPullParser.TEXT) {
            result = Integer.parseInt(parser.getText());
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
