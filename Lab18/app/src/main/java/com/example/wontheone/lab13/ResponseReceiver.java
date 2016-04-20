package com.example.wontheone.lab13;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-14.
 */
public class ResponseReceiver extends BroadcastReceiver {

    public static final String ACTION_RESP = "com.example.intent.action.MESSAGE_PROCESSED";
    String TAG = "ResponseReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TextView result = (TextView) findViewById(R.id.txt_result);
        ArrayList<XmlParser.Player> players =
                (ArrayList<XmlParser.Player>) intent.getSerializableExtra(XMLDownloadService.PARAM_OUT_MSG);
        // result.setText(text);
        // delete old data (like this?)
        context.getApplicationContext().getContentResolver().delete(MyProvider.CONTENT_URI, null, null);
        for (XmlParser.Player p : players){
            ContentValues values = new ContentValues();
            values.put(MyProvider._ID, p.getId());
            values.put(MyProvider.NAME, p.getName());
            context.getContentResolver().insert(MyProvider.CONTENT_URI, values);
        }
    }
}
