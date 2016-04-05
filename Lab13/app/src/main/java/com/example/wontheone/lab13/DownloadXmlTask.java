package com.example.wontheone.lab13;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-23.
 */
public class DownloadXmlTask extends AsyncTask<String, Void, String> {

    AppCompatActivity observer;

    public void register(AppCompatActivity observer){
        this.observer = observer;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            Log.d("url", urls[0]);
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
            // Log.d("io exception", e.getStackTrace().toString());
            return e.getStackTrace().toString();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            // Log.d("io exception", e.getStackTrace().toString());
            return e.getStackTrace().toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Displays the HTML string in the UI via a WebView
        WebView myWebView = (WebView) observer.findViewById(R.id.webview);
        Log.d("string", result);
        Log.d("webView", myWebView.toString());
        myWebView.loadData(result, "text/html", null);
    }

    // Uploads XML, parses it, and combines it with
    // HTML markup. Returns HTML string.
    private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        // Instantiate the parser
        XmlParser xmlParser = new XmlParser();
        List<XmlParser.Player> players = null;
        int id = 0;
        String url = null;
        String name = null;

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h3> Players </h3>");

        try {
            stream = downloadUrl(urlString);
            players = xmlParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // xmlParser returns a List (called "players") of Entry objects.
        // Each player object represents a single player in the XML.
        // This section processes the players list to combine each player with HTML markup.
        for (XmlParser.Player player : players) {
            htmlString.append("<p>");
            htmlString.append(player.name +"("+ player.id +")</p>");
        }
        return htmlString.toString();
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }
}
