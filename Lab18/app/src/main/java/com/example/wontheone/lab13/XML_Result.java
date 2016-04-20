package com.example.wontheone.lab13;


import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class XML_Result extends AppCompatActivity implements MyObserver, LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listView;
    SimpleCursorAdapter myAdapter;
    private ResponseReceiver receiver;

    private static final String XML_SOURCE_URL = "http://users.metropolia.fi/~peterh/players.xml";

    static final String[] PROJECTION = new String[]{MySQLiteHelper.KEY_PLAYER_Id, MySQLiteHelper.KEY_PLAYERNAME};
    static final String SELECTION = "";

    public final PlayerListAdapter adapter = new PlayerListAdapter(this, new ArrayList<XmlParser.Player>());
    static Cursor getCursor;
    static ArrayList<XmlParser.Player> players;
    public static SQLiteDatabase newDB;
    private static String Select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml__result);
        String[] fromColumns = {MySQLiteHelper.KEY_PLAYER_Id, MySQLiteHelper.KEY_PLAYERNAME};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        myAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, fromColumns, toViews, 0);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(myAdapter);
        // A LoaderManager.LoaderCallbacks implementation, which the LoaderManager calls
        // to report loader events. In this example, the local class implements the
        // LoaderManager.LoaderCallbacks interface, so it passes a reference to itself, this.
        // If the loader specified by the ID does not exist,
        // initLoader() triggers the LoaderManager.
        // LoaderCallbacks method onCreateLoader().
        // This is where you implement the code to instantiate and return a new loader.
        // Note that the initLoader() method returns the Loader that is created,
        // but you don't need to capture a reference to it.
        // The LoaderManager manages the life of the loader automatically.
        getLoaderManager().initLoader(0, null, this);

        // To create a work request and send it to an IntentService, create an explicit Intent,
        // add work request data to it, and send it to IntentService by calling startService().

        /*
         * Creates a new Intent to start the RSSPullService
         * IntentService. Passes a URI in the
         * Intent's "data" field.
         */
        Intent mServiceIntent = new Intent(this, XMLDownloadService.class);
        mServiceIntent.putExtra(XMLDownloadService.PARAM_IN_MSG, XML_SOURCE_URL);
        // Starts the IntentService
        // Once you call startService(), the IntentService does the work defined
        // in its onHandleIntent() method, and then stops itself.
        startService(mServiceIntent);
        // Register the receiver in the onCreate() method with the appropriate intent filter
        // to catch the specific result intent being sent from the IntentService.
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
        //        DownloadXmlTask downloadTask = new DownloadXmlTask();
        //        downloadTask.register(this);
        //        downloadTask.execute(XML_SOURCE_URL);

        //        MySQLiteHelper dbHelper = new MySQLiteHelper(this.getApplicationContext());
        //        newDB = dbHelper.getWritableDatabase();
        //
        //        Select = "SELECT " + MySQLiteHelper.KEY_PLAYERNAME + ", " + MySQLiteHelper.KEY_PLAYER_Id
        //                + " FROM " + MySQLiteHelper.PLAYERS_TABLE_NAME + " ";
        //        // Register as observer
        //        players = new ArrayList<>();
        //        Model.getInstance().register(this);

        // getData();
        // ListView listView = (ListView) findViewById(R.id.listView);
        // listView.setAdapter(adapter);
    }

    public void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        getCursor = newDB.rawQuery(Select, null);
//        if (getCursor != null && getCursor.getCount() != 0) {
//            // from the first player
//            if (getCursor.moveToFirst()) {
//                do {
//                    XmlParser.Player player = new XmlParser.Player
//                            (Integer.parseInt(getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYER_Id))),
//                                    getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYERNAME)));
//                    Log.d("player: ", player.getId() + " " + player.getName());
////                    Model.getInstance().addPlayer(new XmlParser.Player
////                            (Integer.parseInt(getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYER_Id))),
////                                    getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYERNAME))));
//                } while (getCursor.moveToNext());
//                // while there are next players
//            }
//        } else {
//            Log.d("getCursor", "getCursor != null && getCursor.getCount() != 0");
//        }
//        getCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_player:
                openAddPlayerActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAddPlayerActivity() {
        Intent openAddPlayerIntent = new Intent(this, AddPlayerActivity.class);
        // openAddPlayerIntent.putExtra("context", (Context) this);
        startActivity(openAddPlayerIntent);
    }

    @Override
    public void updateUi(List<XmlParser.Player> players) {
        final List<XmlParser.Player> finalPlayers = players;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addPlayers(finalPlayers);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public PlayerListAdapter getAdapter() {
        return adapter;
    }

    public static void getData() {

        getCursor = newDB.rawQuery(Select, null);
        Model.getInstance().getPlayers().clear();
        if (getCursor != null && getCursor.getCount() != 0) {
            // from the first player
            if (getCursor.moveToFirst()) {
                do {
                    XmlParser.Player player = new XmlParser.Player
                            (Integer.parseInt(getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYER_Id))),
                                    getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYERNAME)));
                    Model.getInstance().addPlayer(player);
//                    Model.getInstance().addPlayer(new XmlParser.Player
//                            (Integer.parseInt(getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYER_Id))),
//                                    getCursor.getString(getCursor.getColumnIndexOrThrow(MySQLiteHelper.KEY_PLAYERNAME))));
                } while (getCursor.moveToNext());
                // while there are next players
            }
        } else {
            Log.d("getCursor", "getCursor != null && getCursor.getCount() != 0");
        }
        getCursor.close();
    }

    // Instantiate and return a new Loader for the given ID.
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MyProvider.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }

    //  Called when a previously created loader has finished its load.
    // In either case, the given LoaderManager.LoaderCallbacks implementation is associated with
    // the loader, and will be called when the loader state changes.
    // If at the point of this call the caller is in its started state,
    // and the requested loader already exists and has generated its data,
    // then the system calls onLoadFinished() immediately (during initLoader()),
    // so you must be prepared for this to happen

//    public void onLoadFinished(Loader loader, Object data) {
//        myAdapter.swapCursor((Cursor) data);
//    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        myAdapter.swapCursor(c);
    }

    // Called when a previously created loader is being reset, thus making its data unavailable.
    @Override
    public void onLoaderReset(Loader loader) {
        myAdapter.swapCursor(null);
    }
    //  sometimes you want to discard your old data and start over.
    //  To discard your old data, you use restartLoader()


//    @Override
//    protected void onPause() {
//        unregisterReceiver(receiver);
//        super.onPause();
//    }
}
