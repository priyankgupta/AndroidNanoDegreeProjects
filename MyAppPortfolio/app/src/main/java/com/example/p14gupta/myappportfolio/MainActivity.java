package com.example.p14gupta.myappportfolio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {
    MediaPlayer oursong;
    Button B1,B2,B3,B4,B5,B6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get Variable Ref from layout xml*/
        B1 = (Button) findViewById(R.id.button1);
        B2 = (Button) findViewById(R.id.button2);
        B3 = (Button) findViewById(R.id.button3);
        B4 = (Button) findViewById(R.id.button4);
        B5 = (Button) findViewById(R.id.button5);
        B6 = (Button) findViewById(R.id.button6);

        oursong = MediaPlayer.create(MainActivity.this,R.raw.sound);

        /*Set up on click listener */
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my Spotify Streamer App", LENGTH_SHORT).show();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my Football Scores App", LENGTH_SHORT).show();

            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my Library App", LENGTH_SHORT).show();
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my Build It Bigger App", LENGTH_SHORT).show();
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my XYZ Reader", LENGTH_SHORT).show();
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oursong.start();
                Toast.makeText(getApplicationContext(),"This Button will launch my Capstone :My Own App", LENGTH_SHORT).show();
            }
        });

    }
}
