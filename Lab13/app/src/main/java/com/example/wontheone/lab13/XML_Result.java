package com.example.wontheone.lab13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class XML_Result extends AppCompatActivity {

    private static final String mandatoryURL = "http://users.metropolia.fi/~peterh/players.xml";
    private static final String extraURL = "http://yle.fi/uutiset/rss/uutiset.rss?osasto=news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml__result);
        String url = getIntent().getExtras().getString("url");
        if (url.equals("mandatory")){
            DownloadXmlTask downloadTask = new DownloadXmlTask();
            downloadTask.register(this);
            downloadTask.execute(mandatoryURL);
        } else if (url.equals("extra")){
            DownloadNewsXmlTask downloadTask = new DownloadNewsXmlTask();
            downloadTask.register(this);
            downloadTask.execute(extraURL);
        }
    }
}
