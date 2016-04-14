package com.example.wontheone.lab13;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-13.
 * It can't interact directly with your user interface.
 * To put its results in the UI, you have to send them to an Activity.
 * Work requests run sequentially. If an operation is running in an IntentService,
 * and you send it another request, the request waits until the first operation is finished.
 * An operation running on an IntentService can't be interrupted.
 */
public class XMLDownloadService extends IntentService {

    @Override
    protected void onHandleIntent(Intent intent) {
        // Gets data from the incoming Intent
        String dataString = intent.getDataString();
        // Do work here, based on the contents of dataString

    }
    // Notice that the other callbacks of a regular Service component,
    // such as onStartCommand() are automatically
    // invoked by IntentService. In an IntentService, you should avoid overriding these callbacks.
}
