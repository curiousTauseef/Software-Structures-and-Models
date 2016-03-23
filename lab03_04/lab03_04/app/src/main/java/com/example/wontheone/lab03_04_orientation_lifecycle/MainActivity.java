package com.example.wontheone.lab03_04_orientation_lifecycle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    CreatureCollection creatureCollection = null;
    String FILENAME = "creatureCollection.ser";
    TextView textView;
    Button btnClear;
    Button btnAdd;
    RadioGroup radioGroup;
    EditText editText;
    String radio1str;
    String radio2str;
    String radio3str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate: ", "called");
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnAdd = (Button) findViewById(R.id.btn_add);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        editText = (EditText) findViewById(R.id.edit_text);
        textView = (TextView) findViewById(R.id.status_message);
        radio1str = getResources().getString(R.string.radio1);
        radio2str = getResources().getString(R.string.radio2);
        radio3str = getResources().getString(R.string.radio3);

        try {
            FileInputStream fileIn = openFileInput(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            creatureCollection = (CreatureCollection) in.readObject();
            if (creatureCollection == null)
                creatureCollection = new CreatureCollection();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            creatureCollection = new CreatureCollection();
            onStop();
        } catch (ClassNotFoundException c) {
            System.out.println("Creature collection class not found");
            c.printStackTrace();
        }

        writeStatusMessage();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                editText.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() != -1
                        && editText.getText().toString().trim().length() > 0) {
                    RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                    if (radioButton.getText().toString().equals(radio1str)) {
                        creatureCollection.add(new Creature(CreatureType.HUMAN, editText.getText().toString()));
                    } else if (radioButton.getText().toString().equals(radio2str)) {
                        creatureCollection.add(new Creature(CreatureType.OTHER_ANIMAL, editText.getText().toString()));
                    } else {
                        creatureCollection.add(new Creature(CreatureType.ROBOT, editText.getText().toString()));
                    }
                    writeStatusMessage();
                } else {
                    textView.setText("Check a radio button and enter some text.");
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop: ", "called");
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(creatureCollection);
            out.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * put all creatures in the file to status message
     */
    private void writeStatusMessage() {
        StringBuilder sb = new StringBuilder();
        for (Creature c : creatureCollection.getCreatureList()){
            sb.append(c);
            sb.append("\n");
        }
        textView.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy: ", "called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause: ", "called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume: ", "called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart: ", "called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart: ", "called");
    }
}
