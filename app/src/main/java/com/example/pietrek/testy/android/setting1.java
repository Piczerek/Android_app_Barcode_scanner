package com.example.pietrek.testy.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pietrek.testy.MainActivity;
import com.example.pietrek.testy.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static java.lang.Thread.sleep;


public class setting1 extends AppCompatActivity /*implements View.OnClickListener*/ {
    private TextView txt,txt2;
    String serverName = "192.168.1.10";
    int port = 6066;
    OutputStream outToServer;
    DataOutputStream out;
    DataInputStream in;

    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txt2= (TextView)findViewById(R.id.textView4);
        txt = (TextView)findViewById(R.id.textView3);


        Intent intent1 = getIntent();


         message = intent1.getStringExtra("message");
        txt.setText(message);
        new Thread(new ClientThread()).start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    out.writeUTF(message);
                    txt2.setText(in.readUTF());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }


   /* public void onClick(View v){
        if (v.getId()==R.id.cipa) {

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        //message = status;//edtText.getText().toString();
                        out.writeUTF(message);
                        txt2.setText(in.readUTF());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }*/


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
            Intent intent = new Intent(setting1.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.lista) {
            return true;
        }
        if (id == R.id.costam) {
            Intent intent = new Intent(setting1.this, Info.class);
            startActivity(intent);
            //  Intent intent = new Intent(MainActivity.this, setting1.class);
            // startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {
            try
            {
                System.out.println("Connecting to " + serverName +
                        " on port " + port);

                Socket client = new Socket(serverName, port);

                System.out.println("Just connected to "
                        + client.getRemoteSocketAddress());

                outToServer = (OutputStream) client.getOutputStream();
                out = new DataOutputStream(outToServer);
                out.writeUTF("Hello from "
                        + client.getLocalSocketAddress());
                InputStream inFromServer = (InputStream) client.getInputStream();

                in =
                        new DataInputStream(inFromServer);
                System.out.println("Server says " + in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        synchronized void writeMessage(String message) {

            final String msg = message;

        }

    }

}
