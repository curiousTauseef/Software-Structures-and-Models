package com.example.wontheone.lab13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity {

    EditText newId;
    EditText newName;
    Button createBtn;
    public static SQLiteDatabase newDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        newId = (EditText) findViewById(R.id.new_id);
        newName = (EditText) findViewById(R.id.new_name);
        createBtn = (Button) findViewById(R.id.CreateBtn);
        final MySQLiteHelper dbHelper = new MySQLiteHelper(this.getApplicationContext());
        newDB = dbHelper.getWritableDatabase();
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new birthday record
                ContentValues values = new ContentValues();
                values.put(MyProvider._ID, newId.getText().toString());
                values.put(MyProvider.NAME, newName.getText().toString());
                Intent intent = getIntent();

                Uri uri = getApplicationContext().getContentResolver().insert(MyProvider.CONTENT_URI, values);
                Toast.makeText(getBaseContext(),
                        uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
//                dbHelper.insert(newDB, new XmlParser.Player(Integer.parseInt(newId.getText().toString()),
//                        newName.getText().toString()));
//                List<XmlParser.Player> newPlayer = new ArrayList<XmlParser.Player>();
//                newPlayer.add(new XmlParser.Player(Integer.parseInt(newId.getText().toString()),
//                        newName.getText().toString()));
//                Model.getInstance().addPlayers(newPlayer);
            }
        });
    }
}
