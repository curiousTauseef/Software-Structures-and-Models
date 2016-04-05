package com.example.wontheone.lab13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity {

    EditText newId;
    EditText newName;
    Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        newId = (EditText) findViewById(R.id.new_id);
        newName = (EditText) findViewById(R.id.new_name);
        createBtn = (Button) findViewById(R.id.CreateBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<XmlParser.Player> newPlayer = new ArrayList<XmlParser.Player>();
                newPlayer.add(new XmlParser.Player(Integer.parseInt(newId.getText().toString()),
                        newName.getText().toString()));
                Model.getInstance().addPlayers(newPlayer);
            }
        });
    }
}
