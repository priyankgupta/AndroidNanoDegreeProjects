package com.example.p14gupta.popularmoviedb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.p14gupta.popularmoviedb.R;

import static android.view.Window.*;

/**
 * Created by p14gupta on 21-05-2016.
 * Intro page for the App
 */

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*Hide the title*/
        requestWindowFeature(FEATURE_NO_TITLE);
        /*code that displays the content in full screen mode*/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Thread wait = new Thread(){
            public void run(){
              try{
                    sleep(1000);
              }catch(InterruptedException e){
                    e.printStackTrace();
                }

              finally {
                  Intent openPopMovieMainpage = new Intent("com.example.p14gupta.popularmoviedb.POPMOVIESMAINPAGE");
                  startActivity(openPopMovieMainpage);
              }
            }
        };
        wait.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
