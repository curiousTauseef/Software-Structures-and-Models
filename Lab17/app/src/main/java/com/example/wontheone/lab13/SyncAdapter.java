//package com.example.wontheone.lab13;
//
//import android.accounts.Account;
//import android.content.AbstractThreadedSyncAdapter;
//import android.content.ContentProviderClient;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.SyncResult;
//import android.os.Bundle;
//
///**
// * Handle the transfer of data between a server and an
// * app, using the Android sync adapter framework.
// * <p/>
// * Created by Won Seob Seo(Metropolia UAS) on 2016-04-04.
// */
//public class SyncAdapter extends AbstractThreadedSyncAdapter {
//
//    // Global variables
//    // Define a variable to contain a content resolver instance
//    ContentResolver mContentResolver;
//
//    /**
//     * Set up the sync adapter
//     */
//    public SyncAdapter(Context context, boolean autoInitialize) {
//        super(context, autoInitialize);
//        /*
//         * If your app uses a content resolver, get an instance of it
//         * from the incoming Context
//         */
//        mContentResolver = context.getContentResolver();
//    }
//
//    /**
//     * Set up the sync adapter. This form of the
//     * constructor maintains compatibility with Android 3.0
//     * and later platform versions
//     */
//    public SyncAdapter(
//            Context context,
//            boolean autoInitialize,
//            boolean allowParallelSyncs) {
//        super(context, autoInitialize, allowParallelSyncs);
//        /*
//         * If your app uses a content resolver, get an instance of it
//         * from the incoming Context
//         */
//        mContentResolver = context.getContentResolver();
//
//    }
//
//    /**
//     * Add the data transfer code, so that the sync adapter
//     * framework can run the data transfer in the background,
//     * without involvement from your app
//     * When the framework is ready to sync your application's data, it invokes your implementation of the method onPerformSync()
//     * Specify the code you want to run in the sync adapter. The entire
//     * sync adapter runs in a background thread, so you don't have to set
//     * up your own background processing.
//     */
//    @Override
//    public void onPerformSync(
//            Account account,
//            Bundle extras,
//            String authority,
//            ContentProviderClient provider,
//            SyncResult syncResult) {
//        /**
//         * Put the data transfer code here.
//         * Connecting to a server: the sync adapter framework doesn't automatically connect to a server.
//         * Downloading and uploading data: you have to provide the code that requests the data, downloads it, and inserts it in the provider.
//         * Handling data conflicts or determining how current the data is:
//         * Clean up: Always close connections to a server and clean up temp files and caches at the end of your data transfer.
//         * In addition to your sync-related tasks, you should try to combine your regular network-related tasks and add them to onPerformSync().
//         * By concentrating all of your network tasks in this method,
//         * you conserve the battery power that's needed to start and stop the network interfaces.
//         */
//
//    }
//}
