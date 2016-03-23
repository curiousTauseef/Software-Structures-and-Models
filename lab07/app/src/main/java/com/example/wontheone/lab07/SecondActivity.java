package com.example.wontheone.lab07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String submitString = extras.getString("submitString");
        final TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(submitString);
    }
}
