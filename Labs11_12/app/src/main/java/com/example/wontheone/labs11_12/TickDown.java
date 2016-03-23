package com.example.wontheone.labs11_12;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-22.
 */
public class TickDown extends AsyncTask<Integer, Double, String>{

    TickDownObserver observer;

    public void registerObserver(TickDownObserver observer){
        this.observer = observer;
    }

    @Override
    protected String doInBackground(Integer... params) {
        int numSteps, remainingNumSteps;
        numSteps = remainingNumSteps = params[0];
        Log.d("steps", numSteps+","+remainingNumSteps);
        int delayInSec = params[1];
        while (remainingNumSteps > 0) {
            try {
                Thread.sleep((long) delayInSec * 1000);
            } catch (InterruptedException i){
                i.printStackTrace();
            }
            remainingNumSteps--;
            Log.d("steps", numSteps+","+remainingNumSteps);
            double progressPercentage = (1.0 - (((long) remainingNumSteps*1.0)/((long) numSteps*1.0)));
            Log.d("percent", Double.toString(progressPercentage));
            publishProgress(progressPercentage);
        }
        return "Done.";
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        observer.setProgressPercentage(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        observer.setDoneMessage(s);
    }
}
