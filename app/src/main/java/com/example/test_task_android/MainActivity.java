package com.example.test_task_android;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CLICK_COUNT, clickCount);
    }
    private void openFragment() {
        MyFragment fragment = new MyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null); // Add the transaction to the back stack
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle the back arrow click in the fragment
        if (item.getItemId() == android.R.id.home) {
            // Go back to the activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
