package com.example.wontheone.lab07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }
}
