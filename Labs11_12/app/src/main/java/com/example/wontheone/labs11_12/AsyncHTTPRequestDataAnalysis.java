package com.example.wontheone.labs11_12;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-22.
 */
public class AsyncHTTPRequestDataAnalysis extends AsyncTask<URL, Integer, Integer> {

    AsyncHTTPRequestDataAnalysisObserver observer;

    public void registerObserver(AsyncHTTPRequestDataAnalysisObserver observer){
        this.observer = observer;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        observer.setLongestLineLength(integer);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        observer.updateNumLines(values[0]);
        observer.setTotalSize(values[1]);
    }

    @Override
    protected Integer doInBackground(URL... params) {
        int longestLineLength, numLines, dataTotalSize;
        longestLineLength = numLines = dataTotalSize = 0;
        if (params.length > 0)
        {
            try {
                HttpURLConnection conn = (HttpURLConnection) params[0].openConnection();
                conn.setDoInput(true);
                conn.connect();
                Log.d("url", params[0].toString());
                Scanner sc = new Scanner(conn.getInputStream());
                String line;
                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    Log.d("line", line);
                    if (line.length() > longestLineLength)
                        longestLineLength = line.length();
                    numLines++;
                    dataTotalSize += line.length();
                    publishProgress(numLines, dataTotalSize);
                }
                sc.close();
                conn.disconnect();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return longestLineLength;
    }
}
