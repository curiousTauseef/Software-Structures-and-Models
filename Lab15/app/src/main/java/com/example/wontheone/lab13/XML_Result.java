package com.example.wontheone.lab13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class XML_Result extends AppCompatActivity implements MyObserver {

    private static final String mandatoryURL = "http://users.metropolia.fi/~peterh/players.xml";
    private static final String extraURL = "http://yle.fi/uutiset/rss/uutiset.rss?osasto=news";
    public final PlayerListAdapter adapter = new PlayerListAdapter(this, new ArrayList<XmlParser.Player>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml__result);
        String url = getIntent().getExtras().getString("url");
        if (url.equals("mandatory")) {
            DownloadXmlTask downloadTask = new DownloadXmlTask();
            downloadTask.register(this);
            downloadTask.execute(mandatoryURL);
        }
        // Register as observer
        Model.getInstance().register(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
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

    private void openAddPlayerActivity(){
        Intent openAddPlayerIntent = new Intent(this, AddPlayerActivity.class);
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
}
