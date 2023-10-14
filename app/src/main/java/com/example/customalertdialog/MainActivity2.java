package com.example.customalertdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView mTextView;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent gi = getIntent();
        mTextView = (TextView) findViewById(R.id.textView12);
    }

    /**
     * This method defines the menu.
     * @param menu The options menu in which you place your items.
     *
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * this method matches the credit to the option that is selected.
     * @param item The menu item that was selected.
     *
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        st = item.getTitle().toString();
        if (st.equals("creator")) {
            mTextView.setText("The creator is Itai Hadar THE KING");
        }
        else if (st.equals("teacher")) {
            mTextView.setText("The teacher is Albert Levy THE Champion");
        }
        else if (st.equals("development environment")) {
            mTextView.setText("development environment is Android Studio");
        }
        return super.onOptionsItemSelected(item);

    }
    /**
     * this method closes this activity.
     * @param view
     */
    public void return_to_main(View view) {
        finish();
    }
}