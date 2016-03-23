package helloandroid.wontheone.com.myfirstapp;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by pghoo on 2016-03-15.
 */

public class MyClickListener implements View.OnClickListener {
    private final String MYNAME = "EventHandlingExample";
    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        Log.d(MYNAME, "separate listener (MyClickListener): " + b.getText() + " clicked");
    }

}
