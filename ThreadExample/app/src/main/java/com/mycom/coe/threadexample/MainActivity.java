package com.mycom.coe.threadexample;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    int i = 0;
    TextView tvNum;
    private Thread thread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNum = (TextView) findViewById(R.id.tvNum);
//        handlerThreadMethod();

        MyTask mytask = new MyTask();
        mytask.execute("MyTask");
    }


    class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            while (i++ < 100)
                try {
                    Thread.sleep(500);
                    System.out.println(i);
                    System.out.println(strings[0]);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            return "Finish";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("OnPre-Excute: ");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("OnPost-Excute: " + s); //Finish
        }

        @Override  // run on Ui Thread
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvNum.setText("Counter: " + values[0]);
        }
    }

    private void handlerThreadMethod() {
        thread = new Thread() {
            @Override
            public void run() {
                while (i++ < 100)
                    try {
                        Thread.sleep(500);
                        System.out.println(i);
                        Message m = new Message();
                        m.arg1 = i;
//                        handler.sendMessage(m);
                        handler.sendMessageDelayed(m, 3000);
//                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
            }
        };
        thread.start();

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvNum.setText("Counter: " + msg.arg1);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}


