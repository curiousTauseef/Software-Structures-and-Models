package com.example.wontheone.lab13;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.sql.SQLException;

/**
 * To access a provider, your application usually
 * has to request specific permissions in its manifest file.
 * This is described in more detail in the section Content Provider Permissions
 * All forms of access eventually call ContentResolver,
 * which then calls a concrete method of ContentProvider to get access.
 * Created by Won Seob Seo(Metropolia UAS) on 2016-04-12.
 */
public class MyProvider extends ContentProvider {

    // The SQLiteOpenHelper class helps you create databases,
    private MySQLiteHelper dbHelper;
    // and the SQLiteDatabase class is the base class for accessing databases.
    // Remember that you don't have to use a database to implement your repository.
    // A provider appears externally as a set of tables, similar to a relational database,
    // but this is not a requirement for the provider's internal implementation.
    private SQLiteDatabase thisDB;
    // where the PROVIDER_NAME = "com.example.wontheone.lab20" string is the provider's authority,
    // and the 'players' string is the table's path
    // A provider usually has a single authority, which serves as its Android-internal name.
    // To avoid conflicts with other providers, you should use Internet domain ownership
    // (in reverse) as the basis of your provider authority.
    // Because this recommendation is also true for Android package names,
    // you can define your provider authority as an extension of
    // the name of the package containing the provider.
    // For example, if your Android package name is com.example.<appname>,
    // you should give your provider the authority com.example.<appname>.provider.
    public static final String PROVIDER_NAME = "com.example.wontheone.lab20.provider";
    public static final String TABLE_NAME = "players";
    // Developers usually create content URIs from the authority by appending paths that point to
    // individual tables. For example, if you have two tables table1 and table2, you combine
    // the authority from the previous example to yield the content URIs
    // com.example.<appname>.provider/table1 and com.example.<appname>.provider/table2
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/" + TABLE_NAME);

    // Although you can use any name for this column, using BaseColumns._ID is the best choice,
    // because linking the results of a provider query to a ListView
    // requires one of the retrieved columns to have the name _ID.
    public static final String _ID = MySQLiteHelper.KEY_PLAYER_Id;
    public static final String NAME = MySQLiteHelper.KEY_PLAYERNAME;

    private static final int ALL_PLAYERS = 1;
    private static final int SINGLE_PLAYER = 2;

    // By convention, providers offer access to a single row in a table by accepting a content URI
    // with an ID value for the row at the end of the URI. Also by convention, providers
    // match the ID value to the table's _ID column, and perform the requested access
    // against the row that matches.
    // This convention facilitates a common design pattern for apps accessing a provider.
    // The app does a query against the provider and displays the resulting Cursor
    // in a ListView using a CursorAdapter.
    // The definition of CursorAdapter requires one of the columns in the Cursor to be _ID

    // To help you choose which action to take for an incoming content URI,
    // the provider API includes the convenience class UriMatcher,
    // which maps content URI "patterns" to integer values. You can use the integer values in a
    // switch statement that chooses the desired action
    // for the content URI or URIs that match a particular pattern.
    // A content URI pattern matches content URIs using wildcard characters:
    //    *: Matches a string of any valid characters of any length.
    //    #: Matches a string of numeric characters of any length.
    // handles URIs for an entire table differently from URIs for a single row,
    // by using the content URI pattern content://<authority>/<path> for tables,
    // and content://<authority>/<path>/<id> for single rows.

    // The method addURI() maps an authority and path to an integer value.
    // The method match() returns the integer value for a URI.
    // A switch statement chooses between querying the entire table, and querying for a single record:

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME, ALL_PLAYERS);
        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME + "/#", SINGLE_PLAYER);
    }

    // Delete rows from your provider.
    // Use the arguments to select the table and the rows to delete.
    // Return the number of rows deleted.
    // The delete() method does not have to physically delete rows from your data storage.
    // If you are using a sync adapter with your provider, you should consider marking a deleted
    // row with a "delete" flag rather than removing the row entirely.
    // The sync adapter can check for deleted rows and remove them from
    // the server before deleting them from the provider.
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case ALL_PLAYERS:
                // delete all the records of the table
                count = thisDB.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case SINGLE_PLAYER:
                String id = uri.getLastPathSegment(); //gets the id
                count = thisDB.delete( TABLE_NAME, _ID +  " = " + id +
                (!TextUtils.isEmpty(selection) ? " AND (" +
                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    // Return the MIME type corresponding to a content URI
    // The getType() method returns a String in MIME format that describes the type of data
    // returned by the content URI argument.
    // The Uri argument can be a pattern rather than a specific URI; in this case,
    // you should return the type of data associated with content URIs that match the pattern.
    public String getType(Uri uri) {
        // For content URIs that point to a row or rows of table data, getType()
        // should return a MIME type in Android's vendor-specific MIME format:
        //        Type part: vnd
        //        Subtype part:
        //        If the URI pattern is for a single row: android.cursor.item/
        //                If the URI pattern is for more than one row: android.cursor.dir/
        //                Provider-specific part: vnd.<name>.<type>
        //                You supply the <name> and <type>. The <name> value should be globally unique,
        // and the <type> value should be unique to the corresponding URI pattern. A good choice for <name> is your company's name or some part of your application's Android package name.
        //                A good choice for the <type> is a string that identifies the table associated with the URI.
        switch (uriMatcher.match(uri)) {
            case ALL_PLAYERS:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME + TABLE_NAME;
            case SINGLE_PLAYER:
                return "vnd.android.cursor.item/vnd." + PROVIDER_NAME + TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    // A method that you're expected to implement if your provider offers files.
    @Override
    public String[] getStreamTypes(Uri uri, String mimeTypeFilter) {
        return super.getStreamTypes(uri, mimeTypeFilter);
    }

    // Insert a new row into your provider.
    // Use the arguments to select the destination table and to get the column values to use.
    // Return a content URI for the newly-inserted row.
    //  If a column name is not in the ContentValues argument,
    // you may want to provide a default value for it either in your provider code or in your database schema.
    // This method should return the content URI for the new row.
    // To construct this, append the new row's _ID (or other primary key)
    // value to the table's content URI, using withAppendedId().
    public Uri insert(Uri uri, ContentValues values)  {
        /*
         * Gets a writeable database. This will trigger its creation if it doesn't already exist.
         *
         */
        thisDB = dbHelper.getWritableDatabase();
        // TODO Auto-generated method stub
        long row = thisDB.insert(TABLE_NAME, "", values);
        // If record is added successfully
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getApplicationContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        Log.d("Debug","Fail to add a new record into ");
        return null;
    }

    // The Android system calls onCreate() when it starts up the provider.
    // You should perform only fast-running initialization tasks in this method,
    // and defer database creation and data loading until the provider
    // actually receives a request for the data. If you do lengthy tasks in onCreate(),
    // you will slow down your provider's startup.
    // In turn, this will slow down the response from the provider to other applications.
    public boolean onCreate() {
        // For example, if you are using an SQLite database
        // you can create a new SQLiteOpenHelper object in ContentProvider.onCreate(),
        // and then create the SQL tables the first time you open the database.
        // To facilitate this, the first time you call getWritableDatabase(),
        // it automatically calls the SQLiteOpenHelper.onCreate() method.
        dbHelper = new MySQLiteHelper(getContext());
        // thisDB = dbHelper.getReadableDatabase();
        thisDB = dbHelper.getWritableDatabase();
//        if (thisDB == null)
//            return false;
//        else
        return true;
    }

    // Implements ContentProvider.query()
    // Retrieve data from your provider. Use the arguments to select the table to query,
    // the rows and columns to return, and the sort order of the result.
    // Return the data as a Cursor object.or if it fails, throw an Exception.
    // If you are using an SQLite database as your data storage, you can simply return the Cursor
    // returned by one of the query() methods of the SQLiteDatabase class.
    // If the query does not match any rows, you should return a Cursor instance whose getCount() method returns 0.
    // You should return null only if an internal error occurred during the query process.
    // Remember that the Android system must be able to communicate the Exception across process boundaries.
    // Android can do this for the following exceptions that may be useful in handling query errors:
    // IllegalArgumentException (You may choose to throw this if your provider receives an invalid content URI)
    // NullPointerException
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(MySQLiteHelper.PLAYERS_TABLE_NAME);
        // Many providers allow you to access a single row in a table by appending an
        // ID value to the end of the URI. For example, to retrieve a row whose _ID is
        // 4 from user dictionary, you can use this content URI:
        // Uri singleUri = ContentUris.withAppendedId(UserDictionary.Words.CONTENT_URI,4);
        // The Uri and Uri.Builder classes contain convenience methods for
        // constructing well-formed URI objects from strings. The ContentUris class contains
        // convenience methods for appending id values to a URI. The previous snippet uses withAppendedId() to append an id to the UserDictionary content URI.
        /*
         * Choose the table to query and a sort order based on the code returned for the incoming
         * URI. Here, too, only the statements for table 3 are shown.
         */
        switch (uriMatcher.match(uri)) {
            // If the incoming URI was for all of table3
            case ALL_PLAYERS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;
            // If the incoming URI was for a single row
            case 2:
                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                selection = selection + "_ID = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException();
                // If the URI is not recognized, you should do some error handling here.
        }
        if (uriMatcher.match(uri) == SINGLE_PLAYER)
            sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
        Cursor cur = sqlBuilder.query(thisDB, projection, selection, selectionArgs,
                null, null, sortOrder);
        cur.setNotificationUri(getContext().getContentResolver(), uri);
        return cur;
    }

    // Update existing rows in your provider. Use the arguments to select the table and rows to
    // update and to get the updated column values. Return the number of rows updated.
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
