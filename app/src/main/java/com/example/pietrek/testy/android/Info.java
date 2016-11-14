package com.example.pietrek.testy.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pietrek.testy.MainActivity;
import com.example.pietrek.testy.R;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.scanner) {
            Intent intent = new Intent(Info.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.lista) {
            Intent intent = new Intent(Info.this, setting1.class);
            startActivity(intent);
        }
        if (id == R.id.costam) {
            return true;
            //  Intent intent = new Intent(MainActivity.this, setting1.class);
            // startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
