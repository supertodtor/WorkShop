package com.mycom.coe.threadexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {
            @Override
            public void run() {
                while( i++ < 5 ){
                    System.out.println("Counter: " + i);
                }
            }
        };
        thread.start();

    }
}
