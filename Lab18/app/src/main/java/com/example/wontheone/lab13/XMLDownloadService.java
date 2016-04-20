package com.example.wontheone.lab13;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-13.
 * It can't interact directly with your user interface.
 * To put its results in the UI, you have to send them to an Activity.
 * Work requests run sequentially. If an operation is running in an IntentService,
 * and you send it another request, the request waits until the first operation is finished.
 * An operation running on an IntentService can't be interrupted.
 */
public class XMLDownloadService extends IntentService {

    String TAG = "XMLDownloadService";

    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public XMLDownloadService() {
        super("XMLDownloadService");
    }

    // You will need to implement just one other method called onHandleIntent().
    // This method is where your processing occurs.
    // Any data necessary for each processing request can be packaged in the intent extras, like so
    @Override
    protected void onHandleIntent(Intent intent) {
        // Gets data from the incoming Intent
        while (true)
            try {
                ArrayList<XmlParser.Player> players = (ArrayList) loadXmlFromNetwork(intent.getStringExtra(PARAM_IN_MSG));
                Log.d(TAG, "onHandleIntent: ");
                // processing done here….
                // To send the result back to the main application, we package up another intent,
                // stick the result data in as an extra, and blast it back using the sendBroadcast() method.

                getApplicationContext().getContentResolver().delete(MyProvider.CONTENT_URI, null, null);
                for (XmlParser.Player p : players){
                    ContentValues values = new ContentValues();
                    values.put(MyProvider._ID, p.getId());
                    values.put(MyProvider.NAME, p.getName());
                    getContentResolver().insert(MyProvider.CONTENT_URI, values);
                }
//                Intent broadcastIntent = new Intent()
//                        .setAction(ResponseReceiver.ACTION_RESP)
//                        .addCategory(Intent.CATEGORY_DEFAULT)
//                        .putExtra(PARAM_OUT_MSG, players);
//                sendBroadcast(broadcastIntent); // Intent is broadcasted here
                SystemClock.sleep(10000); // 5 seconds wait
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    // Notice that the other callbacks of a regular Service component,
    // such as onStartCommand() are automatically
    // invoked by IntentService. In an IntentService, you should avoid overriding these callbacks.

    private List<XmlParser.Player> loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        // Instantiate the parser
        XmlParser xmlParser = new XmlParser();
        List<XmlParser.Player> players = null;
        int id = 0;
        String url = null;
        String name = null;
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
        return players;
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
