package helloandroid.wontheone.com.myfirstapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private final String debugString = "counter val:";
    private Counter counter = new Counter();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        counter.setValue(settings.getInt("counter", 5));

        textView = (TextView) findViewById(R.id.textView);
        Log.d(debugString, "after setContentView()");
        // increment button
        Button incrementBtn = (Button) findViewById(R.id.button_increment);
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                counter.increment();
                textView.setText(Integer.toString(counter.getValue()));
                Log.d(debugString, Integer.toString(counter.getValue()));
            }
        });
        incrementBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button b = (Button) v;
                counter.longClickIncrement();
                Log.d(debugString, Integer.toString(counter.getValue()));
                textView.setText(Integer.toString(counter.getValue()));
                return true;
            }
        });

        // decrement button
        Button decrementBtn = (Button) findViewById(R.id.button_decrement);
        decrementBtn.setOnClickListener(this);
        decrementBtn.setOnLongClickListener(this);
        // reset button
        Button resetBtn = (Button) findViewById(R.id.button_reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                counter.reset();
                Log.d(debugString, Integer.toString(counter.getValue()));
                textView.setText(Integer.toString(counter.getValue()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        counter.decrement();
        Log.d(debugString, Integer.toString(counter.getValue()));
        textView.setText(Integer.toString(counter.getValue()));
    }

    @Override
    public boolean onLongClick(View v) {
        counter.longClickDecrement();
        textView.setText(Integer.toString(counter.getValue()));
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        SharedPreferences settings = getPreferences(MODE_PRIVATE);
//        counter.setValue(settings.getInt("counter", 5));
    }

    @Override
    protected void onStop() {
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("counter", counter.getValue());
        editor.commit();
        super.onStop();
    }
}
