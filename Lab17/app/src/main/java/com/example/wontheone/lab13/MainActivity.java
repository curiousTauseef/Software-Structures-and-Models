package com.example.wontheone.lab13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button manBtn, extraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manBtn = (Button) findViewById(R.id.mandatory);
        extraBtn = (Button) findViewById(R.id.extra);
        manBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMandatoryPage();
            }
        });
        extraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadExtraPage();
            }
        });
    }

    // Uses AsyncTask to download the XML feed
    public void loadMandatoryPage() {
        Intent intent = new Intent(this, XML_Result.class);
        intent.putExtra("url", "mandatory");
        startActivity(intent);
    }

    // Uses AsyncTask to download the XML feed
    public void loadExtraPage() {
        Intent intent = new Intent(this, XML_Result.class);
        intent.putExtra("url", "extra");
        startActivity(intent);
    }

}
