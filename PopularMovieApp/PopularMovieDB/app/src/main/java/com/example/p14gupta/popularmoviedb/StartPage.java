package com.example.p14gupta.popularmoviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import static android.view.Window.*;

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
                    sleep(3000);
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
