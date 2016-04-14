
/*
package com.example.abdullai.lab16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Abdulla on 2/29/2016.
 */
/*
public class MySQLiteHelper extends SQLiteOpenHelper {


    private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";

    private static String DB_NAME = "myDBName";

    private SQLiteDatabase myDataBase;

    private final Context myContext;



    private String  Name;
    private String  Num;
    private String  Posstion;
    private String  Salery;
    private int     Img;


    static final String DB_PATH = "/data/data/com.example.abdullai.lab16/databases/";
    static final String DATABASE_NAME = "playersDB.db";

    static final int DATABASE_VERSION = 1;

    private final Context myContext;


    static final String PLAYERS_TABLE_NAME = "players";
    static final String KEY_PLAYERNAME = "Name";
    static final String KEY_PLAYER_Id = "0";
    static final String KEY_PLAYERPOSS = "None";
    static final String KEY_PLAYERSAL = "0";
    static final String KEY_PLAYERIMG = "0" ;
    public static final String KEY_PLAYERID = "_id";



    private static final String DATABASE_CREATE =
            "create table " + PLAYERS_TABLE_NAME + " (" +
            KEY_PLAYERID + " integer primary key autoincrement, " +
                    KEY_PLAYERNAME + " text not null," +
                    KEY_PLAYER_Id + " text not null," +
                    KEY_PLAYERPOSS + " text not null," +
                    KEY_PLAYERSAL + " text, " +
                    KEY_PLAYERIMG + " integer" +
                    ");";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
        onCreate(db);

    }
}

/

public class MySQLiteHelper extends SQLiteOpenHelper{

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.abdullai.lab16/databases/";

    private static String DB_NAME = "myDBName";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     /
    public MySQLiteHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * /
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     /
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * /
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}



*/
package com.example.wontheone.lab13;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

/**
 * Helper class that actually creates and manages the provider's underlying data repository.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    public SQLiteDatabase DB;
    public String DBPath;
    public String name;
    public static String DBName = "teamapp";
    public static final int version = '1';
    public static Context currentContext;

    public static final String[] pNames = {"Gandalf", "Dambldor.", "Hari Poter", "Hagrid", "Smeagol", "Sirijus Blek", "Hodor", "Frodo", "Seloba",
            "Sersei", "John Snow", "Ajra", "Semvajs", "Edard stark", "Kejtlin stark",
            "Khal Drogo", "Setlok holms", "Malkom", "Khalisi",  "Daenerys Targaryen",
            "Dr John Watson",  "Vermaelen",  "Hagrid",  "Seloba",  "Jack Sparrow"};

    public static final String[] pIds = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};

    public static final String PLAYERS_TABLE_NAME = "players";
    public static final String KEY_PLAYERNAME = "name";
    public static final String KEY_PLAYER_Id = "_id";
    // A provider isn't required to have a primary key,
    // and it isn't required to use _ID as the column name of a primary key if one is present.
    // However, if you want to bind data from a provider to a ListView, one of the column names has to be _ID.
    // This requirement is explained in more detail in the section Displaying query results.

    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS  " + PLAYERS_TABLE_NAME + " (" +
                    KEY_PLAYER_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_PLAYERNAME + " TEXT NOT NULL" +
                    ");";

    public MySQLiteHelper(Context context) {
        super(context, DBName, null, version);
        name = DBName;
        currentContext = context;
        Log.d("package name", context.getPackageName());
        DBPath = "/data/data/" + context.getPackageName() + "/databases/";
        Log.d("DB path", DBPath);
        createDatabase();
    }

    /*
     * Creates the data repository. This is called when the provider attempts to open the
     * repository and SQLite reports that it doesn't exist.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        createDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME;
        db.execSQL(drop);
        onCreate(db);
// TODO Auto-generated method stub
    }

    public long insert(SQLiteDatabase db, XmlParser.Player player) {
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_Id, player.getId());
        values.put(KEY_PLAYERNAME, player.getName());
        Model.getInstance().addPlayer(player);
        Log.d("player: ", player.getId() + " " + player.getName());
        return db.insert(PLAYERS_TABLE_NAME, null, values);
    }

    private void createDatabase() {
        //boolean dbExists = checkDbExists();
        DB = currentContext.openOrCreateDatabase(DBName, 0, null);
        DB.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
        // Creates the "players" table
        DB.execSQL(DATABASE_CREATE);
//            "CREATE TABLE IF NOT EXISTS  " + PLAYERS_TABLE_NAME + " (" +
//                    KEY_PLAYERNAME + " text not null," +
//                    KEY_PLAYER_Id + " integer primary key" +
//                    ");";
//        for (int i = 0; i<pNames.length-1; i++)
//        {
//            DB.execSQL("INSERT INTO " + PLAYERS_TABLE_NAME + " (" + KEY_PLAYERNAME + ", "+ KEY_PLAYER_Id +") " +
//                    "Values ('" + pNames[i] + "', " + pIds[i] + ");");
//        }

//        if (dbExists) {
//            // do nothing
//        } else {
//            // db doesn't exist, create DB
//            // openOrCreateDatabase(String path, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler)
//            DB = currentContext.openOrCreateDatabase(DBName, 0, null);
//            DB.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
//            DB.execSQL(DATABASE_CREATE);
////            "CREATE TABLE IF NOT EXISTS  " + PLAYERS_TABLE_NAME + " (" +
////                    KEY_PLAYERNAME + " text not null," +
////                    KEY_PLAYER_Id + " integer primary key" +
////                    ");";
//            for (int i = 0; i<pNames.length-1; i++)
//            {
//                DB.execSQL("INSERT INTO " + PLAYERS_TABLE_NAME + " (" + KEY_PLAYERNAME + ", "+ KEY_PLAYER_Id +") " +
//                        "Values ('" + pNames[i] + "', " + pIds[i] + ");");
//            }
//        }
    }

    private boolean checkDbExists() {
        String myPath = DBPath + DBName;
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Log.d("db error","db doesn't exist yet");
        }
        return checkDB != null;
    }
}