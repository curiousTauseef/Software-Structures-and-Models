package com.example.wontheone.labs11_12;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TickDownObserver, AsyncHTTPRequestDataAnalysisObserver {

    EditText numSteps, delay, inputUrl;
    Button startButton, startHTTPRequest;
    TextView percentText, doneMessage, numLines, totalDataSize, longestLineLength;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numSteps = (EditText) findViewById(R.id.numStep);
        delay = (EditText) findViewById(R.id.delay);
        inputUrl = (EditText) findViewById(R.id.inputUrl);
        startButton = (Button) findViewById(R.id.startButton);
        startHTTPRequest = (Button) findViewById(R.id.startHTTPRequest);
        percentText = (TextView) findViewById(R.id.percentageText);
        doneMessage = (TextView) findViewById(R.id.doneMessage);
        numLines = (TextView) findViewById(R.id.numLine);
        totalDataSize = (TextView) findViewById(R.id.totalDataSize);
        longestLineLength = (TextView) findViewById(R.id.longestLineLength);
        startButton.setOnClickListener(this);
        startHTTPRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = inputUrl.getText().toString();
                if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
                    urlString = "http://" + urlString;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                AsyncHTTPRequestDataAnalysis httpReq = new AsyncHTTPRequestDataAnalysis();
                httpReq.registerObserver(MainActivity.this);
                httpReq.execute(url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int steps, delayInSeconds;
        steps = Integer.parseInt(numSteps.getText().toString());
        delayInSeconds = Integer.parseInt(delay.getText().toString());
        TickDown tickDown = new TickDown();
        tickDown.registerObserver(this);
        tickDown.execute(steps, delayInSeconds);
    }

    @Override
    public void setProgressPercentage(Double d){
        percentText.setText(d.toString());
    }

    @Override
    public void setDoneMessage(String s){
        doneMessage.setText(s);
    }

    @Override
    public void updateNumLines(int num){
        numLines.setText(Integer.toString(num));
    }

    public void setTotalSize(int num){
        totalDataSize.setText(Integer.toString(num));
    }

    public void setLongestLineLength(int num){
        longestLineLength.setText(Integer.toString(num));
    }
}
