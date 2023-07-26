package com.example.test_task_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CLICK_COUNT = "click_count";

    private int clickCount = 0;
    private TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonClick = findViewById(R.id.buttonClick);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                textViewCounter.setText(String.valueOf(clickCount));
            }
        });
        if (savedInstanceState != null) {
            clickCount = savedInstanceState.getInt(KEY_CLICK_COUNT, 0);
            textViewCounter.setText(String.valueOf(clickCount));
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CLICK_COUNT, clickCount);
    }
}
